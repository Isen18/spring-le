package springmvc.le.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Isen
 * @date 2020/3/25 11:52
 * @since 1.0
 */
@WebServlet(value = "/async-servlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        //原始请求可以做一些简单处理
        final PrintWriter writer = response.getWriter();
        writer.println("老板检查当前需要做的工作");
        writer.flush();

        //如果不通过使用AsyncContext，而是直接new thread处理慢任务，并不能释放http thread pool
        AsyncContext ac = request.startAsync();
        //具体处理请求，内部使用工作线程，不会阻塞当前线程，该方法执行完毕，会释放http thread，但是不会关闭连接，即不会进行http返回
        //所有处理的数据的上下文都在AsyncContext中
        doWork(ac);
        writer.println("老板布置完工作就走了");
        writer.flush();
    }

    private void doWork(AsyncContext asyncContext) {
        //设置超时属性，超过时间会抛出异常，可以通过监听器进行监听
//        asyncContext.setTimeout(3000);
//        asyncContext.addListener();
        //不用asyncContext.start，tomcat会使用http thread pool中的线程去执行慢任务
        ThreadPoolUtil.WORK_THREAD_POOL.execute(() -> {
            try {
                PrintWriter writer = asyncContext.getResponse().getWriter();
                TimeUnit.SECONDS.sleep(3);
                writer.write("处理任务");
                asyncContext.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

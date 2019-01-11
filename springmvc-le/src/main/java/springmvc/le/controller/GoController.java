package springmvc.le.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Isen
 * @date 2019/1/7 23:16
 * @since 1.0
 */
@Controller
public class GoController implements EnvironmentAware {
    private final Log logger = LogFactory.getLog(GoController.class);

    /**
     * HEAD请求适合检查服务器状态，不会返回body，节省网络资源
     */
    @RequestMapping(value = {"/"}, method = {RequestMethod.HEAD})
    public String head(){
        return "go.jsp";
    }

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(Model model) throws Exception{
        logger.info("========index=========");
        model.addAttribute("msg", "Go!");
        return "go.jsp";
    }

    private Environment enviroment = null;

    @Override
    public void setEnvironment(Environment environment) {
        this.enviroment = environment;
    }
}

package springmvc.le.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Isen
 * @date 2019/1/3 9:26
 * @since 1.0
 */
// TODO isen 2019/1/11 源码追踪
@Controller
@RequestMapping("/myController")
public class MyController {

    private final static String OK = "ok";

    /**
     * url地址会变为跳转的目标地址，并在目标地址后面+?name=isen
     *
     * curl http://localhost:8080/myController/redirect
     */
    // TODO isen 2019/1/11 为毛url+?name=isen
    @RequestMapping("/redirect")
    public String redirect(Model model){
        System.out.println("==============test==============");
        model.addAttribute("name", "isen");
        return "redirect:test";
    }

    /**
     * url地址会变为跳转的目标地址，并在目标地址后面+?age=18
     *
     * curl http://localhost:8080/myController/redirect2
     */
    // TODO isen 2019/1/11 为毛只是url+?age=18
    @RequestMapping("/redirect2")
    public String redirect(Model model, RedirectAttributes redirectAttributes){
        System.out.println("==============test==============");
        model.addAttribute("name", "isen");
        redirectAttributes.addAttribute("age", 18);
        redirectAttributes.addFlashAttribute("sex", "man");
        return "redirect:test";
    }

    /**
     * url地址不变
     *
     * curl http://localhost:8080/myController/forward
     */
    // TODO isen 2019/1/11 为毛传递不到test
    @RequestMapping("/forward")
    public String forward(Model model, HttpServletRequest request){
        System.out.println("==============test==============");
        model.addAttribute("name", "isen");
        request.setAttribute("name2", "isen2");
        return "forward:test";
    }

    /**
     * curl http://localhost:8080/myController/test
     */
    @RequestMapping("/test")
    public @ResponseBody String test(String name, Integer age, String sex, Model model, HttpServletRequest request){
        System.out.println("name=" + name);
        System.out.println("age=" + age);
        System.out.println("sex=" + sex);
        System.out.println("model.name=" + model.asMap().get("name"));
        System.out.println("request.name2=" + request.getParameter("name2"));
        return OK;
    }

}

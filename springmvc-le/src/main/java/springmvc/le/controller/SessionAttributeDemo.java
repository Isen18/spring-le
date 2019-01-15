package springmvc.le.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author Isen
 * @date 2019/1/13 16:16
 * @since 1.0
 */
@Controller
@RequestMapping("/SessionAttributeDemo")
@SessionAttributes(value = {"book", "description"}, types = Double.class)
public class SessionAttributeDemo {

    /**
     * curl http://localhost:8080/SessionAttributeDemo/index
     */
    @RequestMapping("/index")
    public String index(Model model){
        //以下model参数也会在url上,即默认情况下，model中参数会在redirect的url中暴露出来，即添加在url后面
        model.addAttribute("book", "空境");
        model.addAttribute("description", "空之境界");
        model.addAttribute("price", 18.);
//        model.addAttribute("price", new Double(18.));
        return "redirect:get";
    }

    @RequestMapping("/get")
    public String getBySessionAttributes(@ModelAttribute("book") String book, ModelMap modelMap, SessionStatus sessionStatus){
        System.out.println("book=" + book);
        System.out.println("description=" + modelMap.get("description"));
        System.out.println("price=" + modelMap.get("price"));

        sessionStatus.setComplete();
        return "redirect:complete";
    }

    @RequestMapping("complete")
    @ResponseBody
    public String complete(
            //@ModelAttribute("book") String book, //因为sessionAttribute中不存在book了, 会报HTTP Status 500 - Expected session attribute 'book'
            ModelMap modelMap,
            Double price//url链接上会有price等参数
    ){
//        System.out.println("book=" + book);
        System.out.println("book=" + modelMap.get("book"));
        System.out.println("description=" + modelMap.get("description"));
        System.out.println("price=" + price);
        return "OK";
    }
}

package springmvc.le.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Isen
 * @date 2019/1/3 9:26
 * @since 1.0
 */
@Controller
@RequestMapping("/ParamBand")
//@SessionAttributes(value = {"name", "password", "sky"}, types = {Integer.class})
public class ParamBandController {

    /**
     * curl http://localhost:8080/ParamBand/test
     */
    @RequestMapping("/test")
    public String test(Model model){
        System.out.println("==============test==============");
        model.addAttribute("name", "isen");
        return "redirect:test2";
    }

    /**
     * curl http://localhost:8080/ParamBand/test2
     */
    @RequestMapping("/test2")
    public String test2(){
        System.out.println();
        return "";
    }

//    /**
//     * curl http://localhost:8080/ParamBand/test2?age=12
//     */
//    @RequestMapping("/test2")
//    public String test2(@ModelAttribute("name") String name, @ModelAttribute("age") Integer age, ModelMap modelMap, SessionStatus sessionStatus){
//        System.out.println("==============test2==============");
//        System.out.println(name);
//        System.out.println(age);
//        System.out.println(modelMap.get("age"));
////        sessionStatus.setComplete();
//        return "redirect:/ParamBand5/test";
//    }

    /**
     * curl http://localhost:8080/ParamBand/test3
     */
    @RequestMapping("/test3")
    public @ResponseBody
    String test3(@ModelAttribute("name") String name, @ModelAttribute("color") String color, Model model){
        System.out.println("==============test3==============");
        System.out.println(name);
        System.out.println(color);
        model.addAttribute("sky", "sky");
        return "ok";
    }
}

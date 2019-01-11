package springmvc.le.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Isen
 * @date 2019/1/3 9:26
 * @since 1.0
 */
@Controller
@RequestMapping("/ParamBand2")
//@SessionAttributes(value = {"color"})
public class ParamBandController2 {

    /**
     * curl http://localhost:8080/ParamBand2/test?color=red
     */
    @RequestMapping("/test")
    public String test(Model model){
        System.out.println("==============test==============");
        model.addAttribute("color", "red");
        return "redirect:test2";
    }

    /**
     * curl http://localhost:8080/ParamBand2/test2
     */
    @RequestMapping("/test2")
    public String test2(){
        System.out.println("==============test==============");
        return "redirect:/ParamBand4/test3";
    }
}

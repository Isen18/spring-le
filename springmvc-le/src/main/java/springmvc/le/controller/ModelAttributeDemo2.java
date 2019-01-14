package springmvc.le.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Isen
 * @date 2019/1/13 20:50
 * @since 1.0
 */
@Controller
@RequestMapping("ModelAttributeDemo2")
public class ModelAttributeDemo2 {

    @ModelAttribute("word")
    public String initWord(){
        return "hello Controller!";
    }

    /**
     * curl http://localhost:8080/ModelAttributeDemo2/getWorld
     * @ModelAttribute 全局优先于handler内部的
     */
    @RequestMapping("/getWorld")
    @ResponseBody
    public String getWord(ModelMap modelMap, @ModelAttribute("word") String word2, String word){
        System.out.println("modelMap.word=" + modelMap.get("word"));
        System.out.println("word=" + word);
        System.out.println("word2=" + word2);
        return "OK";
    }

}

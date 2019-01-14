package springmvc.le.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Isen
 * @date 2019/1/13 20:48
 * @since 1.0
 */
@ControllerAdvice
public class ModelAttributeDemo {

    @ModelAttribute("word")
    public String initWord(){
        return "hello ControllerAdvice!";
    }
}

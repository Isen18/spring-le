package springmvc.le.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springmvc.le.converter.PhoneNumberAnnotationFormatter;
import springmvc.le.pojo.FormatterPOJO;
import springmvc.le.pojo.Tell;

/**
 * @author Isen
 * @date 2019/1/15 14:36
 * @since 1.0
 */
@RestController
@RequestMapping("/FormatterController")
public class FormatterController {
    private final static String OK = "ok";

    /**
     * curl http://localhost:8080/FormatterController/testMyFormatter?tell=010-12345678
     */
    @RequestMapping("/testMyFormatter")
    public String testMyFormatter(Tell tell){
        System.out.println("tell=" + tell);
        return OK;
    }

    /**
     * curl http://localhost:8080/FormatterController/testMyAnnotationFormatter?tell=010-12345678
     */
    @RequestMapping("/testMyAnnotationFormatter")
    public String testMyAnnotationFormatter(FormatterPOJO formatterPOJO, @PhoneNumberAnnotationFormatter Tell tell){
        System.out.println("formatterPOJO=" + formatterPOJO);
        System.out.println("tell=" + tell);
        return OK;
    }
}

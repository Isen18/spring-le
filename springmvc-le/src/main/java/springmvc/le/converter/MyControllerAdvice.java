package springmvc.le.converter;

import java.util.Date;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Isen
 * @date 2019/1/11 16:37
 * @since 1.0
 */
@ControllerAdvice
public class MyControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDatePropertyEditor());
//        binder.addCustomFormatter(new DateFormatter());
    }
}

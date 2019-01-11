package springmvc.le.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2019/1/11 16:28
 * @since 1.0
 */
@RestController
@RequestMapping("converter")
public class TypeConverterController {

    /**
     * curl http://localhost:8080/converter/submit?date=Sat, 01 May 2018 16:30:00 GMT
     * curl http://localhost:8080/converter/submit?date=2018-01-01
     */
    @RequestMapping("submit")
    public String submit(Date date){
        System.out.println(date);
        return date.toString();
    }

    /**
     * 加入自定义属性编辑器。只能供当前controller使用
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("TypeConverterController.initBinder");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}

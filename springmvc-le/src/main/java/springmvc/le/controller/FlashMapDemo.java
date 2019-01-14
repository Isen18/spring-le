package springmvc.le.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Isen
 * @date 2019/1/3 9:26
 * @since 1.0
 */
@Controller
@RequestMapping("/FlashMapDemo")
public class FlashMapDemo {

    private final static String OK = "ok";

    /**
     * url地址会变为跳转的目标地址
     *
     * curl http://localhost:8080/FlashMapDemo/redirect
     */
    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){
        System.out.println("==============test==============");
        //1.通过RequestContextHolder获取request，然后在获取OUTPUT_FLASH_MAP_ATTRIBUTE，然后将数据设置到outputFlashMap中
//        ((FlashMap)((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getAttribute(
//                DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE)).put("name", "o do kay");

        // 获取outputFlashMap
//        RequestContextUtils.getOutputFlashMap(request);

        //2. 等价1.
        ((FlashMap)request.getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE)).put("name", "o do kay");

        //3. 等价2.
        redirectAttributes.addFlashAttribute("sex", "female");

        //4. 会将age=18拼接到url中
        redirectAttributes.addAttribute("age", 18);

        //5. 等价4.
//        model.asMap().put("age", 18);
        return "redirect:test";
    }

    /**
     * curl http://localhost:8080/FlashMapDemo/test
     */
    @RequestMapping("/test")
    public @ResponseBody String test(Integer age, ModelMap modelMap){
        System.out.println("age=" + age);
        System.out.println("modelMap.name=" + modelMap.get("name"));
        System.out.println("modelMap.age=" + modelMap.get("age"));
        System.out.println("modelMap.sex=" + modelMap.get("sex"));
        return OK;
    }

}

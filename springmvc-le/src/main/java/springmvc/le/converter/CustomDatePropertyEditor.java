package springmvc.le.converter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期类型属性编辑器
 */
public class CustomDatePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String source) {
        System.out.println("CustomDateConverter.convert");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
             Date date = simpleDateFormat.parse(source);
             setValue(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

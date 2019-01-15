package springmvc.le.converter;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;
import springmvc.le.pojo.Tell;

/**
 * @author Isen
 * @date 2019/1/7 14:54
 * @since 1.0
 */
public class PhoneNumberFormatter implements Formatter<Tell> {
    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
    @Override
    public String print(Tell tell, Locale locale) {
        if(tell == null) {
            return "";
        }
        return new StringBuilder().append(tell.getAreaCode()).append("-")
                .append(tell.getPhoneNumber()).toString();
    }

    @Override
    public Tell parse(String text, Locale locale) throws ParseException {
        if(!StringUtils.hasLength(text)) {
            return null;
        }

        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()) {
            Tell tell = new Tell();
            tell.setAreaCode(matcher.group(1));
            tell.setPhoneNumber(matcher.group(2));
            return tell;
        } else {
            throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
        }
    }
}
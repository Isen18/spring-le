package springmvc.le.converter;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;
import springmvc.le.pojo.PhoneNumber;

/**
 * @author Isen
 * @date 2019/1/7 14:54
 * @since 1.0
 */
public class PhoneNumberFormatter implements Formatter<PhoneNumber> {
    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
    @Override
    public String print(PhoneNumber phoneNumber, Locale locale) {
        if(phoneNumber == null) {
            return "";
        }
        return new StringBuilder().append(phoneNumber.getAreaCode()).append("-")
                .append(phoneNumber.getPhoneNumber()).toString();
    }

    @Override
    public PhoneNumber parse(String text, Locale locale) throws ParseException {
        if(!StringUtils.hasLength(text)) {
            return null;
        }

        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setAreaCode(matcher.group(1));
            phoneNumber.setPhoneNumber(matcher.group(2));
            return phoneNumber;
        } else {
            throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
        }
    }
}
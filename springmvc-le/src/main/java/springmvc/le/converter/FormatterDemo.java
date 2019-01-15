package springmvc.le.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import springmvc.le.pojo.FormatterPOJO;
import springmvc.le.pojo.Tell;

/**
 * @author Isen
 * @date 2019/1/15 11:48
 * @since 1.0
 */
public class FormatterDemo {
    @Test
    public void test() throws ParseException {
        CurrencyStyleFormatter currencyStyleFormatter = new CurrencyStyleFormatter();
        //保留小数点后几位
        currencyStyleFormatter.setFractionDigits(2);
        //舍入模式（ceilling表示四舍五入）
        currencyStyleFormatter.setRoundingMode(RoundingMode.CEILING);

        Assert.assertEquals(new BigDecimal("123.13"), currencyStyleFormatter.parse("$123.125", Locale.US));
        Assert.assertEquals("$123.00", currencyStyleFormatter.print(new BigDecimal("123"), Locale.US));
        Assert.assertEquals("￥123.00", currencyStyleFormatter.print(new BigDecimal("123"), Locale.CHINA));
        Assert.assertEquals("￥123.00", currencyStyleFormatter.print(new BigDecimal("123"), Locale.JAPAN));
    }

    @Test
    public void test2() {
        CurrencyStyleFormatter currencyStyleFormatter = new CurrencyStyleFormatter();
        //保留小数点后几位
        currencyStyleFormatter.setFractionDigits(2);
        //舍入模式（ceilling表示四舍五入）
        currencyStyleFormatter.setRoundingMode(RoundingMode.CEILING);

        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        //注册Formatter SPI实现
        conversionService.addFormatter(currencyStyleFormatter);

        //绑定Locale信息到ThreadLocal
        //FormattingConversionService内部自动获取作为Locale信息，如果不设值默认是 Locale.getDefault()
        LocaleContextHolder.setLocale(Locale.US);
        Assert.assertEquals("$1,234.13", conversionService.convert(new BigDecimal("1234.128"), String.class));

        LocaleContextHolder.setLocale(Locale.CHINA);
        Assert.assertEquals("￥1,234.13", conversionService.convert(new BigDecimal("1234.128"), String.class));
        Assert.assertEquals(new BigDecimal("1234.13"), conversionService.convert("￥1,234.13", BigDecimal.class));
    }

    @Test
    public void test3() throws SecurityException, NoSuchFieldException {
        //默认自动注册对@NumberFormat和@DateTimeFormat的支持
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        //准备测试模型对象
        FormatterPOJO formatterPOJO = new FormatterPOJO();
        formatterPOJO.setTotalCount(10000);
        formatterPOJO.setDiscount(0.51);
        formatterPOJO.setSumMoney(10000.13);
        formatterPOJO.setRegisterDate(Date.from(LocalDateTime.of(2018, 1, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant()));
        formatterPOJO.setOrderDate(Date.from(LocalDateTime.of(2018, 1, 1, 0, 0, 1).atZone(ZoneId.systemDefault()).toInstant()));

        //获取类型信息
        TypeDescriptor descriptor = new TypeDescriptor(FormatterPOJO.class.getDeclaredField("totalCount"));
        TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);

        Assert.assertEquals("10,000", conversionService.convert(formatterPOJO.getTotalCount(), descriptor, stringDescriptor));
        Assert.assertEquals(formatterPOJO.getTotalCount(), conversionService.convert("10,000", stringDescriptor, descriptor));

        descriptor = new TypeDescriptor(FormatterPOJO.class.getDeclaredField("registerDate"));
        Assert.assertEquals("2018-01-01", conversionService.convert(formatterPOJO.getRegisterDate(), descriptor, stringDescriptor));
        Assert.assertEquals(formatterPOJO.getRegisterDate(), conversionService.convert("2018-01-01", stringDescriptor, descriptor));

        descriptor = new TypeDescriptor(FormatterPOJO.class.getDeclaredField("orderDate"));
        Assert.assertEquals("2018-01-01 00:00:01", conversionService.convert(formatterPOJO.getOrderDate(), descriptor, stringDescriptor));
        Assert.assertEquals(formatterPOJO.getOrderDate(), conversionService.convert("2018-01-01 00:00:01", stringDescriptor, descriptor));
    }

    @Test
    public void test4() throws NoSuchFieldException {
        //测试自定义注解格式化转换器

        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        //添加自定义注解格式化工厂
        conversionService.addFormatterForFieldAnnotation(new PhoneNumberFormatAnnotationFormatterFactory());

        FormatterPOJO formatterPOJO = new FormatterPOJO();
        TypeDescriptor descriptor = new TypeDescriptor(FormatterPOJO.class.getDeclaredField("phoneNumber"));
        TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);

        //解析
        Tell value = (Tell) conversionService.convert("010-12345678", stringDescriptor, descriptor);
        formatterPOJO.setTell(value);

        //格式化
        Assert.assertEquals("010-12345678", conversionService.convert(formatterPOJO.getTell(), descriptor, stringDescriptor));
    }
}

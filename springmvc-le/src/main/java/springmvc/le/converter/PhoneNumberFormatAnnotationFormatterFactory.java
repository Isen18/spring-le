package springmvc.le.converter;

import java.util.HashSet;
import java.util.Set;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import springmvc.le.pojo.Tell;

/**
 * @author Isen
 * @date 2019/1/15 14:21
 * @since 1.0
 */
public class PhoneNumberFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<PhoneNumberAnnotationFormatter> {

    private final Set<Class<?>> fieldTypes;

    private final PhoneNumberFormatter formatter;

    public PhoneNumberFormatAnnotationFormatterFactory() {
        Set<Class<?>> set = new HashSet<>();
        set.add(Tell.class);
        this.fieldTypes = set;
        this.formatter = new PhoneNumberFormatter();
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    @Override
    public Parser<?> getParser(PhoneNumberAnnotationFormatter annotation, Class<?> fieldType) {
        return formatter;
    }

    @Override
    public Printer<?> getPrinter(PhoneNumberAnnotationFormatter annotation, Class<?> fieldType) {
        return formatter;
    }
}
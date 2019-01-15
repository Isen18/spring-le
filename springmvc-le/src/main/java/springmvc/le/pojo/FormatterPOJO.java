package springmvc.le.pojo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import springmvc.le.converter.PhoneNumberAnnotationFormatter;

/**
 * @author Isen
 * @date 2019/1/15 13:55
 * @since 1.0
 */
public class FormatterPOJO {

    @NumberFormat(style = Style.NUMBER, pattern = "#,###")
    private int totalCount;

    @NumberFormat(style = Style.PERCENT)
    private double discount;

    @NumberFormat(style = Style.CURRENCY)
    private double sumMoney;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    //自定义注解格式化转换器
    @PhoneNumberAnnotationFormatter
    private Tell tell;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Tell getTell() {
        return tell;
    }

    public void setTell(Tell tell) {
        this.tell = tell;
    }

    @Override
    public String toString() {
        return "FormatterPOJO{" +
                "totalCount=" + totalCount +
                ", discount=" + discount +
                ", sumMoney=" + sumMoney +
                ", registerDate=" + registerDate +
                ", orderDate=" + orderDate +
                ", tell=" + tell +
                '}';
    }
}

package springmvc.le.pojo;

/**
 * @author Isen
 * @date 2019/1/7 14:56
 * @since 1.0
 */
public class Tell {

    private String areaCode;

    private String phoneNumber;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Tell{" +
                "areaCode='" + areaCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

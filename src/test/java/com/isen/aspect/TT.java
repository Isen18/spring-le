package com.isen.aspect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * @author Isen
 * @date 2018/12/28 18:16
 * @since 1.0
 */
public class TT {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Stu stu = new Stu();

        Field field = Stu.class.getDeclaredField("name");
        AccessibleObject.setAccessible(new AccessibleObject[]{field}, true);
        field.set(stu, "张三");
        System.out.println(stu.getName());
    }
}

class Stu{
    private String name;

    public String getName() {
        return name;
    }
}
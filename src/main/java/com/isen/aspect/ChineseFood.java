package com.isen.aspect;

import org.springframework.stereotype.Component;

/**
 * @author Isen
 * @date 2018/12/26 23:33
 * @since 1.0
 */
@Component
public class ChineseFood implements Food {

    @Override
    public void eat() {
        System.out.println("我吃中餐");
    }
}

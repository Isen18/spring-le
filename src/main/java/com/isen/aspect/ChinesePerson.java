package com.isen.aspect;

import org.springframework.stereotype.Component;

/**
 * @author Isen
 * @date 2018/12/26 23:32
 * @since 1.0
 */
@Component
public class ChinesePerson implements Person {

    @Override
    public void say() {
        System.out.println("我是中国人");
    }
}

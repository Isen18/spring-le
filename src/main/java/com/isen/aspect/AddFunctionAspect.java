package com.isen.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * @author Isen
 * @date 2018/12/26 23:34
 * @since 1.0
 */
@Aspect
public class AddFunctionAspect {
    @DeclareParents(value = "com.isen.aspect.Person+", defaultImpl = ChineseFood.class)
    public static Food food;
}

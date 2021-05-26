package com.nacol.TheRoadToGeek.common.annotation;


import java.lang.annotation.*;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/26
 * @Description HttpFilter
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpFilter {

}

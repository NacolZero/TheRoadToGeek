package com.nacol.TheRoadToGeek.week_06_07.database.datasource.dynamic;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceConfig {

    String name() default "";

}

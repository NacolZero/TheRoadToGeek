package com.nacol.TheRoadToGeek.week_06_07.lambda;

import lombok.Data;

@Data
public class Runner {

    private int speed;

    public void say() {
        System.out.println("累死我了");
    }
}

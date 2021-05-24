package com.nacol.TheRoadToGeek.other.supplier;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;

import java.util.function.Supplier;

public class CompleteFutureMain {

    public static void main(String[] args) {
        //一种工厂模式
        Supplier<String> strSup = String::new;
        System.out.println(strSup.get());
        Supplier<HttpRequestDto> requestSup = () ->{
            HttpRequestDto requestDto = new HttpRequestDto();
            requestDto.setUrl("123");
            return requestDto;
        };
        System.out.println(requestSup.get());
        //每次创建新的对象
        System.out.println(requestSup.get() == requestSup.get());


//        CompletableFuture<String> dady = CompletableFuture.supplyAsync()
    }

}

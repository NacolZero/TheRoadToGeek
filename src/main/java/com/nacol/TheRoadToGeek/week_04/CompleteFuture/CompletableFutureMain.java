package com.nacol.TheRoadToGeek.week_04.CompleteFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureMain {

    public static void main(String[] args) {
        String input = "nacol : ";
        CompletableFuture
            .supplyAsync(()-> input)
            .thenApply(s->s + " hahaha")
            .thenApply(String::toUpperCase)
            .thenAccept(System.out::println);
    }

}

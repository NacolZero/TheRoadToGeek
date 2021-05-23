package com.nacol.TheRoadToGeek.week_03.task_5_multithreading.callable;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.task_5_multithreading.woker.TaskHandler;

import java.util.concurrent.Callable;

public class TaskHandlerPlus implements Callable<HttpResponseDto> {

    private HttpRequestDto request;

    public TaskHandlerPlus(HttpRequestDto request) {
        this.request = request;
    }

    @Override
    public HttpResponseDto call() throws Exception {
        return new TaskHandler().doTask(request);
    }
}

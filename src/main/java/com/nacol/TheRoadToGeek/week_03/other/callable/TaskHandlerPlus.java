package com.nacol.TheRoadToGeek.week_03.other.callable;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.other.woker.TaskHandler;

import java.util.List;
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

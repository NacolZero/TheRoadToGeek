package com.nacol.TheRoadToGeek.week_03.task_5_multithreading.callable;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.task_5_multithreading.woker.TaskHandler;

import java.util.List;
import java.util.concurrent.Callable;

public class MixTaskHandlerPlus implements Callable<List<HttpResponseDto>> {

    private HttpRequestDto request;

    public MixTaskHandlerPlus(HttpRequestDto request) {
        this.request = request;
    }

    @Override
    public List<HttpResponseDto> call() throws Exception {
        return new TaskHandler().doMix(request);
    }
}

package com.nacol.TheRoadToGeek.week_03.pool;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;

public class TaskHandler {

    public HttpResponseDto doTask(HttpRequestDto httpRequestDto) {
        return new HttpResponseDto().setStatus(200);
    }

    public HttpResponseDto doError(HttpRequestDto httpRequestDto) {
        int num = 1/0;
        return new HttpResponseDto().setStatus(200);
    }

}

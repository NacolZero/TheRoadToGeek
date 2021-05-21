package com.nacol.TheRoadToGeek.week_03.other.woker;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;

import java.util.ArrayList;
import java.util.List;

public class TaskHandler {

    public HttpResponseDto doTask(HttpRequestDto httpRequestDto) {
        return new HttpResponseDto().setStatus(200);
    }

    public HttpResponseDto doError(HttpRequestDto httpRequestDto) {
        int num = 1/0;
        return new HttpResponseDto().setStatus(200);
    }

    public List<HttpResponseDto> doMix(HttpRequestDto httpRequestDto) {
        List<HttpResponseDto> resps = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                resps.add(doError(httpRequestDto));
            }
            resps.add(doTask(httpRequestDto));
        }
        return resps;
    }

}

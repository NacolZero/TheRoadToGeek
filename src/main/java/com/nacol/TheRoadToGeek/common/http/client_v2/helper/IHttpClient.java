package com.nacol.TheRoadToGeek.common.http.client_v2.helper;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;

public interface IHttpClient {

    HttpResponseDto sendRequest(HttpRequestDto httpRequestDto);

}

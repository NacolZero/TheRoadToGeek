package com.nacol.TheRoadToGeek.week_05.aspect;

import com.nacol.TheRoadToGeek.common.annotation.HttpFilter;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.client.ClientHelperPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpSourceCacheEnum.LOGIN;

@RestController
@RequestMapping("/aspect")
public class AspectController {

    @Autowired
    ClientHelperPlus clientHelperPlus;


    @PostMapping("/sendRequest")
    public String sendRequest(){
        //因为仅仅测试切面，不给参数，会报错。
        HttpRequestDto requestDto = new HttpRequestDto()
                .setServiceCode(LOGIN.serviceCode);
        clientHelperPlus.sendRequest(requestDto);
        return "?";
    }


}

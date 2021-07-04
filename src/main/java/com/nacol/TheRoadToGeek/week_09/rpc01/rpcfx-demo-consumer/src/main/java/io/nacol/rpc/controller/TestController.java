package io.nacol.rpc.controller;

import io.nacol.rpc.demo.api.User;
import io.nacol.rpc.demo.api.UserService;
import io.nacol.rpc.netty.RpcNettyClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    RpcNettyClient rpcClient;

    @PostMapping("/testProxyCache")
    public User testProxyCache(){
        UserService userService = rpcClient.create(UserService.class, "http://localhost:8866/");
        User u = userService.findById(123);
        System.out.println(u);
        return u;
    }

}

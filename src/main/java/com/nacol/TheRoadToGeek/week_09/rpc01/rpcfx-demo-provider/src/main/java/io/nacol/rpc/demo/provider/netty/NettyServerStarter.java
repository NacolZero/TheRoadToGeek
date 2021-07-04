package io.nacol.rpc.demo.provider.netty;

import io.nacol.rpc.demo.provider.netty.RpcNettyServer;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/2
 * @Description 启动 netty server
 */
@Log4j2
@Component
public class NettyServerStarter implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("netty server ready start.......");
            RpcNettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RpcNettyServer.destory();
            log.info("netty server destory.");
        }
    }
}

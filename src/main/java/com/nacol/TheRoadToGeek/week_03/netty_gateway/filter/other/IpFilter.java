package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.other;

import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.IpConfig;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import lombok.extern.log4j.Log4j2;

import java.net.InetSocketAddress;

@Log4j2
public class IpFilter implements IpFilterRule {

    @Override
    public boolean matches(InetSocketAddress remoteAdress) {
       log.info("remote ip : {}", remoteAdress.getAddress());
       //白名单的 ip 地址才可访问 netty
        if (IpConfig.whiteList.contains(remoteAdress.getHostString())) {
            return false;
        }
        return true;
    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.REJECT;
    }
}
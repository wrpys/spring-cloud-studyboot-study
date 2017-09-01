package com.newland.paas.ui.service;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.newland.paas.ui.domain.User;
import com.newland.paas.ui.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author wrp
 * @Description com.newland.paas.ui.service.SomeHystrixService
 * @Date 2017/8/24
 */
@Service
public class SomeHystrixService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackSome")
    public String getSome() {
        return restTemplate.getForObject("http://some/getsome", String.class);
    }

    public String fallbackSome() {
        return "some service模块故障";
    }

    @HystrixCommand(fallbackMethod = "fallbackAddUser")
    public String addUser(User user) {
        String r = restTemplate.postForObject("http://person/save", RestTemplateUtils.buildHttpEntity("wrp"), String.class);
        System.out.println("------:" + r);
        return restTemplate.postForObject("http://some/addUser", RestTemplateUtils.buildHttpEntity(JSON.toJSONString(user)), String.class);
    }

    public String fallbackAddUser(User user) {
        user.setId(0L);
        user.setName("添加用户失败!");
        return JSON.toJSONString(user);
    }

}

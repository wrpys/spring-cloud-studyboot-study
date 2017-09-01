package com.wrpys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @Description com.wrpys.MyAutoConfiguration
 * @Date 2017/7/19
 */
@Configuration
@EnableConfigurationProperties(value = MyProperties.class)
@ConditionalOnClass(MyService.class)
public class MyAutoConfiguration {

    @Autowired
    private MyProperties myProperties;

    @Bean
    @ConditionalOnMissingBean(MyService.class)
    public MyService getMyService() {
        MyService myService = new MyService();
        myService.setMsg(myProperties.getUrl() + ":" + myProperties.getPort());
        return myService;
    }

}

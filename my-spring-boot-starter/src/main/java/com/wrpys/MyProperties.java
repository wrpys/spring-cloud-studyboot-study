package com.wrpys;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wrp
 * @Description com.wrpys.MyProperties
 * @Date 2017/7/19
 */
@ConfigurationProperties(prefix = MyProperties.PREFIX)
public class MyProperties {

    public static final String PREFIX = "my";

    private String url;

    private String port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}

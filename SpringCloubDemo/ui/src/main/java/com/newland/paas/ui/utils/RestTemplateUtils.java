package com.newland.paas.ui.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author wrp
 * @Description RestTemplateU工具类
 * @Date 2017/8/28
 */
public class RestTemplateUtils {

    /**
     * 构造请求参数
     *
     * @param params
     * @return
     */
    public static HttpEntity<String> buildHttpEntity(String params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return new HttpEntity<String>(params, headers);
    }

}

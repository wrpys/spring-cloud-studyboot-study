package com.wrpys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @Description com.wrpys.controller.ErrorController
 * @Date 2017/7/19
 */
@RestController
public class ErrorController {

    @GetMapping("404")
    public String to404() {
        return "地址未找到！";
    }

    @GetMapping("500")
    public String to500() {
        return "服务出错！";
    }

}

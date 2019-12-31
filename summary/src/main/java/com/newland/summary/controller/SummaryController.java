package com.newland.summary.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author WRP
 * @since 2019/12/16
 */
@Controller
public class SummaryController {

    @Value("${taiot-path.agricultural:http://10.1.7.142:8190/taiot/agricultural}")
    private String agriculturalPath;

    @PostMapping(value = "summary")
    public String postSummary() {
        return "redirect:/summary";
    }

    @GetMapping(value = "summary")
    public String getSummary() {
        return "summary/summary.html";
    }

    @PostMapping(value = "gongxiaoyuce")
    public String postGongxiaoyuce() {
        return "redirect:/gongxiaoyuce";
    }

    @GetMapping(value = "gongxiaoyuce")
    public String gettGongxiaoyuce() {
        return "flows/gongxiaoyuce.html";
    }

    @PostMapping(value = "zuiyoushengzhangyinzijisuan")
    public String postZuiyoushengzhangyinzijisuan() {
        return "redirect:/zuiyoushengzhangyinzijisuan";
    }

    @GetMapping(value = "zuiyoushengzhangyinzijisuan")
    public String gettZuiyoushengzhangyinzijisuan() {
        return "flows/zuiyoushengzhangyinzijisuan.html";
    }

    @PostMapping(value = "zhinengpaichan")
    public String postZhinengpaichan() {
        return "redirect:/zhinengpaichan";
    }

    @GetMapping(value = "zhinengpaichan")
    public String getZhinengpaichan() {
        return "flows/zhinengpaichan.html";
    }

    @PostMapping(value = "shishichuanganshujufenxi")
    public String postShishichuanganshujufenxi() {
        return "redirect:/shishichuanganshujufenxi";
    }

    @GetMapping(value = "shishichuanganshujufenxi")
    public String getShishichuanganshujufenxi() {
        return "flows/shishichuanganshujufenxi.html";
    }

    @GetMapping(value = "toLandInfo")
    public void toLandInfo(HttpServletResponse response) throws IOException {
        response.sendRedirect(agriculturalPath + "/toLandInfo.forward");
    }

    @GetMapping(value = "toLandEnvironmentalInfo")
    public void toLandEnvironmentalInfo(HttpServletResponse response) throws IOException {
        response.sendRedirect(agriculturalPath + "/toLandEnvironmentalInfo.forward");
    }

    @GetMapping(value = "toLandCollectorInfo")
    public void toLandCollectorInfo(HttpServletResponse response) throws IOException {
        response.sendRedirect(agriculturalPath + "/toLandCollectorInfo.forward");
    }

}

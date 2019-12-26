package com.newland.summary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WRP
 * @since 2019/12/16
 */
@Controller
public class SummaryController {

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

}

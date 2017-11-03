package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.utils.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wrp
 */
@RestController
@RequestMapping("springdemo")
public class SpringdemoCtr {

    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello() {
        return "hello springboot";
    }

    @PostMapping(value = "json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, Object>> json(@RequestBody User user) {
        return ResponseEntity.ok(buildResponseData());
    }

    //处理文件上传
    @RequestMapping(value = "/uploadimg", method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String filePath = "/home/paas/imgupload/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("respCode", "0");
        header.put("respReq", "20170914162930");
        header.put("respMsg", "图片上传成功");
        result.put("header", header);
        return ResponseEntity.ok(result);
    }

    private Map<String, Object> buildResponseData() {

        Map<String, Object> result = new HashMap<>();

        Map<String, Object> header = new HashMap<>();
        header.put("respCode", "0");
        header.put("respReq", "20170914162930");
        header.put("respMsg", "测试");
        result.put("header", header);

        Map<String, Object> content = new HashMap<>();
        List<User> rows = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setName("name_" + i);
            rows.add(user);
        }
        content.put("rows", rows);
        result.put("content", content);
        return result;
    }

}

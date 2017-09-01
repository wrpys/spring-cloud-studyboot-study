package com.newland.paas.ui.controller;

import com.newland.paas.ui.domain.Person;
import com.newland.paas.ui.domain.User;
import com.newland.paas.ui.service.PersonHystrixService;
import com.newland.paas.ui.service.SomeHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wrp
 * @Description com.newland.paas.ui.controller.UiController
 * @Date 2017/8/24
 */
@RestController
public class UiController {
    @Autowired
    private SomeHystrixService someHystrixService;

    @Autowired
    private PersonHystrixService personHystrixService;

    @RequestMapping("/dispatch")
    public List<Person> sendMessage(@RequestBody String personName) {
        return personHystrixService.save(personName);
    }

    @RequestMapping(value = "/getsome", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String getSome() {

        User user = new User();
        user.setId(1L);
        user.setName("wrp");
        System.out.println(someHystrixService.addUser(user));

        return someHystrixService.getSome();
    }
}

package com.newland.paas.ui.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.newland.paas.ui.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wrp
 * @Description com.newland.paas.ui.service.PersonHystrixService
 * @Date 2017/8/24
 */
@Service
public class PersonHystrixService {

    @Autowired
    PersonService personService;

    @HystrixCommand(fallbackMethod = "fallbackSave")
    public List<Person> save(String name) {
        return personService.save(name);
    }

    public List<Person> fallbackSave(String name) {
        List<Person> persons = new ArrayList<>();
        Person person = new Person("PersonService 故障");
        persons.add(person);
        return persons;
    }

}

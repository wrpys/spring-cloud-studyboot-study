package com.newland.paas.person.controller;

import com.newland.paas.person.dao.PersonRepository;
import com.newland.paas.person.domain.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wrp
 * @Description com.newland.paas.person.controller.PersonController
 * @Date 2017/8/24
 */
@RestController
public class PersonController {

    private static Log log = LogFactory.getLog(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public List<Person> savePerson(@RequestBody String personName) {
        log.info("savePerson===" + personName);
        Person person = new Person(personName);
        personRepository.save(person);
        List<Person> persons = personRepository.findAll(new PageRequest(0, 10)).getContent();
        return persons;
    }

}

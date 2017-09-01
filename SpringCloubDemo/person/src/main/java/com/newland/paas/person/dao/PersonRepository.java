package com.newland.paas.person.dao;

import com.newland.paas.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wrp
 * @Description com.newland.paas.person.dao.PersonRepository
 * @Date 2017/8/24
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}

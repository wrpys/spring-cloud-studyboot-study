package com.newland.paas.some;

import com.newland.paas.some.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SomeApplication {

	private static Log log = LogFactory.getLog(SomeApplication.class);

	@Value("${my.message}")
	private String message;

	@RequestMapping(value = "/getsome")
	public String getsome() {
		log.info(message);
		return message;
	}

	@PostMapping(value = "addUser")
	public User addUser(@RequestBody User user) {
		log.info("addUser===" + user);
		return user;
	}

	public static void main(String[] args) {
		SpringApplication.run(SomeApplication.class, args);
	}
}

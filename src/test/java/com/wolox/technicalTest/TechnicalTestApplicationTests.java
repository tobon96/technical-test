package com.wolox.technicalTest;

import com.wolox.technicalTest.models.entities.User;
import com.wolox.technicalTest.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TechnicalTestApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

}

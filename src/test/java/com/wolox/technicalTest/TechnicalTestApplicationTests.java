package com.wolox.technicalTest;

import com.wolox.technicalTest.models.dtos.PhotosResponseDto;
import com.wolox.technicalTest.services.ApiService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TechnicalTestApplicationTests {

	@Autowired RestTemplate restTemplate;
	@Autowired ApiService apiService;

	@Test
	void contextLoads() throws Exception {

	}

}

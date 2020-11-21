package com.wolox.technicalTest.configurations;

import com.wolox.technicalTest.constants.UtilConstants;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        UriTemplateHandler uriTemplateHandler = new RootUriTemplateHandler(UtilConstants.API_URL);
        return restTemplateBuilder
                .uriTemplateHandler(uriTemplateHandler)
                .build();
    }
}

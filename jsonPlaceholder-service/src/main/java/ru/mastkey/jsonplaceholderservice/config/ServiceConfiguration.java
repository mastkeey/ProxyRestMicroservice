package ru.mastkey.jsonplaceholderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    CloseableHttpClient httpclient() {
        return HttpClients.createDefault();
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

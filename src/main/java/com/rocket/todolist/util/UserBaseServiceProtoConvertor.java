package com.rocket.todolist.util;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
@Configuration
public class UserBaseServiceProtoConvertor {
    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
    @Bean
    public RestTemplate restTemplate(ProtobufHttpMessageConverter protobufHttpMessageConverter) {
        return new RestTemplate(Collections.singletonList(protobufHttpMessageConverter));
    }
}
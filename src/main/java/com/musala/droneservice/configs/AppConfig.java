package com.musala.droneservice.configs;

import com.musala.droneservice.repository.DispatchRepository;
import com.musala.droneservice.service.DispatchService;
import com.musala.droneservice.service.DispatchServiceImpl;
import com.musala.droneservice.utils.i18.MessageService;
import com.musala.droneservice.utils.i18.MessageServiceImpl;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
public class AppConfig {


    @Bean
    public OpenAPI springDroneServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Drone-Service for Medications API")
                        .description("Drone-Service application documentation")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Garage Wiki Documentation")
                        .url("https://test.com/docs"));
    }

    @Bean(name = "customMessageSource")
    public MessageSource customMessageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl(customMessageSource());
    }

    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(25))
                .setReadTimeout(Duration.ofSeconds(25))
                .build();
    }

    @Bean
    public DispatchService dispatchService(DozerBeanMapper mapper, DispatchRepository dispatchRepository, MessageService messageService) {
        return new DispatchServiceImpl( mapper, dispatchRepository, messageService);
    }

}

package com.alex.vis.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    //Need to add Supplier or Consumer function bean for spring-cloud-stream work
    //Spring cloud stream auto detects and bind it with input (Consumer) or output(Supplier) binder
    //We have to use exact same name as binder for this bean - notification-events
    @Bean
    public Consumer<Message<String>> notificationEventSupplier() {
        return message -> {
            new EmailSender().sendEmail(message.getPayload());
        };
    }
}

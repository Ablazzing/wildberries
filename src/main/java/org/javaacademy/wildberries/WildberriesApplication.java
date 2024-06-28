package org.javaacademy.wildberries;

import org.javaacademy.wildberries.dto.OrderDto;
import org.javaacademy.wildberries.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WildberriesApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(WildberriesApplication.class, args);
		OrderService orderService = applicationContext.getBean(OrderService.class);

	}

}

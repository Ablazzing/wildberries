package org.javaacademy.wildberries.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.javaacademy.wildberries.dto.OrderDto;
import org.javaacademy.wildberries.entity.Customer;
import org.javaacademy.wildberries.entity.Good;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class InitService {
    private final CustomerService customerService;
    private final GoodService goodService;
    private final OrderService orderService;
    private final EntityManager entityManager;

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public void init() {
        try {
            Customer customer = customerService.createCustomer("yuri");
            customerService.income(customer, BigDecimal.valueOf(100));
            Good cola = goodService.createGood("cola", 10, BigDecimal.ONE);

            OrderDto orderDto = new OrderDto("yuri", "cola", 10);

            new Thread(() -> orderService.createOrder(orderDto)).start();
            entityManager.clear();
            Thread.sleep(2000);
            new Thread(() -> orderService.createOrder(orderDto)).start();



        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

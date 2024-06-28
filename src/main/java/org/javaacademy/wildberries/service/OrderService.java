package org.javaacademy.wildberries.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
public class OrderService {
    private final CustomerService customerService;
    private final GoodService goodService;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    @SneakyThrows
    public void createOrder(OrderDto orderDto) {
        Customer customer = customerService.getCustomerByName(orderDto.getUsername());
        Good good = goodService.getGoodByName(orderDto.getGoodName());
        System.out.println("Количество товара до списания " + good.getCount());

        if (good.getCount() < orderDto.getCountGood()) {
            throw new RuntimeException("Товара в таком количестве нет на складе");
        }

        BigDecimal totalPrice = good.getPrice().multiply(BigDecimal.valueOf(orderDto.getCountGood()));

        if (customer.getBalance().compareTo(totalPrice) < 0) {
            throw new RuntimeException("У покупателя недостаточно средств");
        }

        goodService.buyGood(good, orderDto.getCountGood());
        System.out.println("Количество товара после списания " + good.getCount());
        Thread.sleep(5000);
        customerService.pay(customer, totalPrice);
    }
}

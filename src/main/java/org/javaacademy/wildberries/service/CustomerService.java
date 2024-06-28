package org.javaacademy.wildberries.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.javaacademy.wildberries.entity.Customer;
import org.javaacademy.wildberries.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Customer getCustomerByName(String name) {
        return customerRepository.findFirstByName(name).orElseThrow();
    }

    public Customer createCustomer(String name) {
        Customer customer = new Customer(name);
        return customerRepository.save(customer);
    }

    @SneakyThrows
    @Transactional
    public void pay(Customer customer, BigDecimal price) {
        BigDecimal newBalance = customer.getBalance().subtract(price);
        customer.setBalance(newBalance);
        customerRepository.save(customer);
        Thread.sleep(3000);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public BigDecimal getBalance(Customer customer) {
        return customerRepository.findBalanceFirstById(customer.getId()).orElseThrow();
    }

    public void income(Customer customer, BigDecimal price) {
        BigDecimal newBalance = customer.getBalance().add(price);
        customer.setBalance(newBalance);
        customerRepository.save(customer);
    }
}

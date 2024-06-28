package org.javaacademy.wildberries.repository;

import org.javaacademy.wildberries.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findFirstByName(String name);

    @Query(nativeQuery = true, value = "select balance from customer where id = ? limit 1")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    Optional<BigDecimal> findBalanceFirstById(Long id);
}

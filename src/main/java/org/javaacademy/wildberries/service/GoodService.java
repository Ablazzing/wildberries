package org.javaacademy.wildberries.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.javaacademy.wildberries.entity.Good;
import org.javaacademy.wildberries.repository.GoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GoodService {
    private final GoodRepository goodRepository;

    public Good createGood(String name, Integer count, BigDecimal price) {
        Good good = new Good(name, count, price);
        return goodRepository.save(good);
    }

    /**
     * Покупка товара (т.е. списание)
     * @param good
     * @param count
     */
    @Transactional
    public void buyGood(Good good, Integer count) {
        good.setCount(good.getCount() - count);
        goodRepository.save(good);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public Good getGoodByName(String name) {
        return goodRepository.findFirstByName(name).orElseThrow();
    }
}

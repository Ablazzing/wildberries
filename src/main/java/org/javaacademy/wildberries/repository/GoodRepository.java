package org.javaacademy.wildberries.repository;

import org.javaacademy.wildberries.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    Optional<Good> findFirstByName(String name);
}

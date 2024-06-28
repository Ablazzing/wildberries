package org.javaacademy.wildberries.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    @Size(min = 2)
    private String name;

    @Column
    @DecimalMin(value = "0")
    private BigDecimal balance = ZERO;

    public Customer(String name) {
        this.name = name;
    }
}

package org.javaacademy.wildberries.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 2)
    private String name;

    @Column
    @Min(value = 0)
    private Integer count;

    @Column
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    public Good(String name, Integer count, BigDecimal price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }
}

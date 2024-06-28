package org.javaacademy.wildberries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private String username;
    private String goodName;
    private Integer countGood;
}

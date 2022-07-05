package com.project.apelsin.dto;

import com.project.apelsin.entity.Customer;
import com.project.apelsin.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLastOrderDto {
    private Long customer_id;
    private String customer_name;
    private LocalDate date;

}

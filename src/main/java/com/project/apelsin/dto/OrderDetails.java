package com.project.apelsin.dto;

import com.project.apelsin.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private Order order;
    private List<DetailsDto> details;
}

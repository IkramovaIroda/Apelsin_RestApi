package com.project.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceWrongDateDto {
    private Long inv_id, ord_id;
    private LocalDate issued_date, ord_date;
}

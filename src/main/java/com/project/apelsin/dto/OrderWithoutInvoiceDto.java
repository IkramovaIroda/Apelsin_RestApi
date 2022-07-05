package com.project.apelsin.dto;

import java.time.LocalDate;

public interface OrderWithoutInvoiceDto {
    Long getId();
    LocalDate getDate();
    Double getTotal();
}

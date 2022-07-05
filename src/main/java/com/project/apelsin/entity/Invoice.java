package com.project.apelsin.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ord_id", nullable = false)
    private Order order;

    @Column(name = "amount", nullable = false, precision = 8, scale = 2)
    private Double amount;

    @Column(name = "issued", nullable = false)
    private LocalDate issued;

    @Column(name = "due", nullable = false)
    private LocalDate due;
}
package com.project.apelsin.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "amount", nullable = false, precision = 8, scale = 2)
    private Double amount;

    @ManyToOne()
    @JoinColumn(name = "inv_id")
    private Invoice inv;
}
package com.project.apelsin.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "ord_id", nullable = false)
    private Order ord;

    @ManyToOne()
    @JoinColumn(name = "pr_id", nullable = false)
    private Product pr;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}
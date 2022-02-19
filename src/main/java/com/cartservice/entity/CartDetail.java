package com.cartservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ProductId;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;
}

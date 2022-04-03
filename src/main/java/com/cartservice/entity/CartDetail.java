package com.cartservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ProductId;
    private int amount;
    private Long attributeId;

    @ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;
}

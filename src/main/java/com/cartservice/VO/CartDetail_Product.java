package com.cartservice.VO;

import com.cartservice.entity.CartDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail_Product {
    private CartDetail cartDetail;
    private Product product;
}

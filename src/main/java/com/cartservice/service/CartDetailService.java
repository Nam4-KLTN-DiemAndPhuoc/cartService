package com.cartservice.service;

import com.cartservice.VO.CartDetail_Product;
import com.cartservice.entity.CartDetail;

import java.util.List;

public interface CartDetailService {
    CartDetail_Product addCartDetailProduct(CartDetail cartDetail);
    CartDetail_Product updateCartDetailProduct(CartDetail cartDetail);
    Long deleteCartDetailProduct(Long id);
    CartDetail_Product findById(Long id);
    List<CartDetail_Product> findByCartId(Long id);
    String deleteCartDetailByCartId(Long id);
}

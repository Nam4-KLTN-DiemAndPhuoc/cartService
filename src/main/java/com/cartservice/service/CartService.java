package com.cartservice.service;

import com.cartservice.VO.Cart_User;
import com.cartservice.entity.Cart;

public interface CartService {
    Cart_User addCart(Cart cart);
    Cart getByUserId(Long id);
    Cart_User findById(Long id);
}

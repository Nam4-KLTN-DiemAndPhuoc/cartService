package com.cartservice.controller;

import com.cartservice.VO.Cart_User;
import com.cartservice.entity.Cart;
import com.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart_User addCart(@RequestBody Cart cart){
        return  cartService.addCart(cart);
    }

    @GetMapping("/user/{id}")
    public Cart getByUserId(@PathVariable Long id){
        return cartService.getByUserId(id);
    }

    @GetMapping("/{id}")
    public Cart_User getById(@PathVariable Long id){
        return cartService.findById(id);
    }
}

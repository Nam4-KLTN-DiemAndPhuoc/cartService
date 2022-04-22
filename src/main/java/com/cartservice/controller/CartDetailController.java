package com.cartservice.controller;

import com.cartservice.VO.CartDetail_Product;
import com.cartservice.entity.CartDetail;
import com.cartservice.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-detail")
@CrossOrigin
public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @PostMapping
    public CartDetail_Product addCartDetail(@RequestBody CartDetail cartDetail){
        return  cartDetailService.addCartDetailProduct(cartDetail);
    }

    @PostMapping("/update")
    public CartDetail_Product updateCartDeatil(@RequestBody CartDetail cartDetail){
        return cartDetailService.updateCartDetailProduct(cartDetail);
    }

    @PostMapping("/delete/{id}")
    public Long deleteByIds(@PathVariable Long id){
        return cartDetailService.deleteCartDetailProduct(id);
    }

    @GetMapping("/{id}")
    public CartDetail_Product findById(@PathVariable Long id){
        return cartDetailService.findById(id);
    }

    @GetMapping("/cart/{id}")
    public List<CartDetail_Product> findByCartId(@PathVariable Long id){
        return  cartDetailService.findByCartId(id);
    }

    @PostMapping("/cart/delete/{id}")
    public String DeleteAllCartDetailByCartId(@PathVariable Long id){
        return cartDetailService.deleteCartDetailByCartId(id);
    }

}

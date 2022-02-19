package com.cartservice.service.impl;

import com.cartservice.VO.CartDetail_Product;
import com.cartservice.VO.Cart_User;
import com.cartservice.VO.User;
import com.cartservice.common.Constants;
import com.cartservice.entity.Cart;
import com.cartservice.entity.CartDetail;
import com.cartservice.repository.CartRepository;
import com.cartservice.service.CartService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Retry(name = "basic")
    @Override
    public Cart_User addCart(Cart cart) {
        Cart crt= repository.save(cart);
        User user=restTemplate.getForObject(Constants.USER+"/"+crt.getUserId(),User.class);
        return new Cart_User(crt, user);
    }

    @Override
    public Cart getByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Retry(name = "basic",fallbackMethod = "getFallback")
    @Override
    public Cart_User findById(Long id) {
        Cart cart= repository.findById(id).get();
        User user=restTemplate.getForObject(Constants.USER+"/"+cart.getUserId(),User.class);
        return new Cart_User(cart,user);
    }
    public Cart_User getFallback(Long id, RuntimeException runtimeException) {
        Cart cart= repository.findById(id).get();
        Cart_User cu= new Cart_User();
        cu.setCart(cart);
        return cu;
    }
}

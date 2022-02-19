package com.cartservice.VO;

import com.cartservice.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart_User {
    private Cart cart;
    private User user;
}

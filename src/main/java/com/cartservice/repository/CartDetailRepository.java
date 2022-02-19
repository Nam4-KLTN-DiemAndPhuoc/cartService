package com.cartservice.repository;

import com.cartservice.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {
    List<CartDetail> findByCartId(Long id);
}

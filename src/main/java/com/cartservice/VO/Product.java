package com.cartservice.VO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String avatar;
    private Long supplierId;
    private double price;
    private float discount;
    private int warranty;
    private Date createdAt;
    private String description;
    private Long createdBy;
    private Long updatedBy;
    private Date updatedAt;
    private Date deletedAt;
    private Long deletedBy;
    private int viewNumber;
    private Long categoryId;
}

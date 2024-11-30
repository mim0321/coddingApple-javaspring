package com.home.shop.sales;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String itemName;
    Integer price;
    Integer count;
    Long memberId;
    @CreationTimestamp
    LocalDateTime created;
}

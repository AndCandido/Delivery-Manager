package com.productdelivery.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRODUCTS_SOLD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSold {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String ref;

    private String name;

    private int quantitySold;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
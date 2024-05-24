package com.productdelivery.core.entities;

import com.productdelivery.core.dtos.delivery.DeliveryInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<ProductSold> productsSold;

    private Address deliveryAddress;

    private DeliveryInfo deliveryInfo;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

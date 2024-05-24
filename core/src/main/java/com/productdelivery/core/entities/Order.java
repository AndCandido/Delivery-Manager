package com.productdelivery.core.entities;

import com.productdelivery.core.dtos.delivery.DeliveryInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Customer customer;
    
    @OneToMany
    private List<ProductSold> productsSold;

    @JdbcTypeCode(SqlTypes.JSON)
    private Address deliveryAddress;

    @JdbcTypeCode(SqlTypes.JSON)
    private DeliveryInfo deliveryInfo;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

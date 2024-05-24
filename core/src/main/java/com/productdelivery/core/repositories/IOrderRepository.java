package com.productdelivery.core.repositories;

import com.productdelivery.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, String> {
}

package com.productdelivery.core.repositories;

import com.productdelivery.core.entities.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductSoldRepository extends JpaRepository<ProductSold, String> {
}

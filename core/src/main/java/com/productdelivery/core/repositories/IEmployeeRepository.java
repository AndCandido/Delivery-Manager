package com.productdelivery.core.repositories;

import com.productdelivery.core.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, String> {
}

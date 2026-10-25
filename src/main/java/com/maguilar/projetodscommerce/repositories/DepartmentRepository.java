package com.maguilar.projetodscommerce.repositories;

import com.maguilar.projetodscommerce.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

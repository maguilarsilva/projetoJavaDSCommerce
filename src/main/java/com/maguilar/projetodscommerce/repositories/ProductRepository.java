package com.maguilar.projetodscommerce.repositories;

import com.maguilar.projetodscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}

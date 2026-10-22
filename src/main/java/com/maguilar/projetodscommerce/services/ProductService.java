package com.maguilar.projetodscommerce.services;

import com.maguilar.projetodscommerce.dto.ProductDTO;
import com.maguilar.projetodscommerce.entities.Product;
import com.maguilar.projetodscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository   productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){

        Optional<Product> result =  productRepository.findById(id);
        Product product = result.get() ;
        return new ProductDTO(product);
    }
}

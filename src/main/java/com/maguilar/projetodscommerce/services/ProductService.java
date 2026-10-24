package com.maguilar.projetodscommerce.services;

import com.maguilar.projetodscommerce.dto.ProductDTO;
import com.maguilar.projetodscommerce.entities.Product;
import com.maguilar.projetodscommerce.exceptions.DataBaseException;
import com.maguilar.projetodscommerce.exceptions.ResourceNotFoundException;
import com.maguilar.projetodscommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository   productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){

       Product product =  productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado."));

        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> listaProduto =  productRepository.findAll(pageable);
        return listaProduto.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insertProduto(ProductDTO productDTO){

        Product produce = new Product();
        copyDtoToEntity(productDTO,produce);
        produce = productRepository.save(produce);
        return new ProductDTO(produce);
    }

    @Transactional
    public ProductDTO updateProduto(Long id, ProductDTO productDTO){
        try {

            Product produce = productRepository.getReferenceById(id);
            copyDtoToEntity(productDTO, produce);
            produce = productRepository.save(produce);
            return new ProductDTO(produce);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encotrado.");
        }
    }
    private void copyDtoToEntity(ProductDTO productDTO, Product produce){

        produce.setName(productDTO.getName());
        produce.setDescription(productDTO.getDescription());
        produce.setPrice(productDTO.getPrice());
        produce.setImgUrl(productDTO.getImgUrl());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteProduto(Long id){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não existe.");
        }
        try{
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Fala de integridade referencial");
        }
    }

}

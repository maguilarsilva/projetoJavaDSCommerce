package com.maguilar.projetodscommerce.controllers;

import com.maguilar.projetodscommerce.dto.ProductDTO;
import com.maguilar.projetodscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value ="/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
         ProductDTO product= productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO> >findAll(Pageable pageable){
        Page<ProductDTO> pageProduct = productService.findAll(pageable);
        return ResponseEntity.ok(pageProduct);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insertProduto(@Valid @RequestBody ProductDTO productDTO){
        productDTO = productService.insertProduto(productDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updatetProduto( @PathVariable Long id,@Valid @RequestBody ProductDTO productDTO ){
        productDTO= productService.updateProduto(id,productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        productService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

}

package com.giacomini.estimateshub.service;

import com.giacomini.estimateshub.dto.ProductDTO;
import com.giacomini.estimateshub.dto.ProductEstimationsDTO;
import com.giacomini.estimateshub.entity.ProductEntity;
import com.giacomini.estimateshub.exception.ProductAlreadyExistsException;
import com.giacomini.estimateshub.exception.ProductNotFoundException;
import com.giacomini.estimateshub.mapper.ProductMapper;
import com.giacomini.estimateshub.repository.ProductRepository;
import com.giacomini.estimateshub.request.ProductRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDTO getById(Long id){
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new
                ProductNotFoundException("Product with id " + id + " does not exist."));

        return ProductMapper.toDTO(product);
    }

    public ProductDTO postProduct(ProductRequest request){

        Optional<ProductEntity> product = productRepository.findByName(request.getProductName());

        if(product.isPresent()){
            throw new ProductAlreadyExistsException("Product with name " + request.getProductName()+ " already exists.");
        }

        return ProductMapper.toDTO(productRepository.save(ProductMapper.toEntity(request)));
    }

    public ProductEntity updateProduct(ProductRequest request, Long id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " does not exist."));

        product.setName(request.getProductName());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProductById(Long id){
        ProductEntity request = productRepository.findById(id).orElseThrow(() -> new
                ProductNotFoundException("Product with id " + id + " does not exist."));

        productRepository.deleteById(request.getId());
    }
}

package com.giacomini.estimateshub.mapper;

import com.giacomini.estimateshub.dto.ProductDTO;
import com.giacomini.estimateshub.dto.ProductEstimationsDTO;
import com.giacomini.estimateshub.entity.ProductEntity;
import com.giacomini.estimateshub.request.ProductRequest;

import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity toEntity(ProductRequest request){
        return ProductEntity.builder()
                .name(request.getProductName())
                .photo(request.getProductPhoto())
                .price(request.getProductPrice())
                .build();
    }

    public static ProductDTO toDTO(ProductEntity entity){
        return ProductDTO.builder()
                .productName(entity.getName())
                .productPhoto(entity.getPhoto())
                .productPrice(entity.getPrice())
                .build();
    }

    public static ProductEstimationsDTO toProductEstimationsDTO(ProductEntity entity){
        return ProductEstimationsDTO.builder()
                .productName(entity.getName())
                .productPhoto(entity.getPhoto())
                .estimationList(entity.getEstimationList().stream().map(EstimationMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}

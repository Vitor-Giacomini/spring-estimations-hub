package com.giacomini.estimateshub.controller;

import com.giacomini.estimateshub.dto.ProductDTO;
import com.giacomini.estimateshub.dto.ProductEstimationsDTO;
import com.giacomini.estimateshub.mapper.ProductMapper;
import com.giacomini.estimateshub.request.ProductRequest;
import com.giacomini.estimateshub.response.BaseResponse;
import com.giacomini.estimateshub.response.BaseResponseError;
import com.giacomini.estimateshub.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(
            value = "Get Product by id",
            response = ProductDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found Product"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping("/{id}")
    public BaseResponse<ProductDTO> getProductById(@PathVariable Long id){
        return BaseResponse.<ProductDTO>builder()
                .httpCode(200)
                .message("OK")
                .response(productService.getById(id))
                .build();
    }

    @ApiOperation(
            value = "Get all Products",
            response = ProductEstimationsDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found all Products"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping()
    public BaseResponse<List<ProductEstimationsDTO>> getAllProducts(){
        return BaseResponse.<List<ProductEstimationsDTO>>builder()
                .httpCode(200)
                .message("OK")
                .response(productService.getAllProducts())
                .build();
    }

    @ApiOperation(
            value = "Post new Product",
            response = ProductDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created Product"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PostMapping()
    public BaseResponse<ProductDTO> postProduct(@RequestBody ProductRequest request){
        return BaseResponse.<ProductDTO>builder()
                .httpCode(201)
                .message("CREATED")
                .response(productService.postProduct(request))
                .build();
    }

    @ApiOperation(
            value = "Put Product by id",
            response = ProductDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated Product"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PutMapping("/{id}")
    public BaseResponse<ProductDTO> putProduct(@RequestBody ProductRequest request, @PathVariable Long id){
        return BaseResponse.<ProductDTO>builder()
                .httpCode(400)
                .message("NO CONTENT")
                .response(ProductMapper.toDTO(productService.updateProduct(request,id)))
                .build();
    }

    @ApiOperation(
            value = "Delete Product by id",
            response = ProductDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted Product"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @DeleteMapping("/{id}")
    public BaseResponse<ProductDTO> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return BaseResponse.<ProductDTO>builder()
                .httpCode(202)
                .message("ACCEPTED")
                .build();
    }
}

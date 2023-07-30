package com.giacomini.estimateshub.controller;

import com.giacomini.estimateshub.dto.ProductDTO;
import com.giacomini.estimateshub.dto.ProductEstimationsDTO;
import com.giacomini.estimateshub.mapper.ProductMapper;
import com.giacomini.estimateshub.request.ProductRequest;
import com.giacomini.estimateshub.response.BaseResponse;
import com.giacomini.estimateshub.response.BaseResponseError;
import com.giacomini.estimateshub.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get Product by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully found Product", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @GetMapping("/{id}")
    public BaseResponse<ProductDTO> getProductById(@PathVariable Long id){
        return BaseResponse.<ProductDTO>builder()
                .httpCode(200)
                .message("OK")
                .response(productService.getById(id))
                .build();
    }

    @Operation(summary = "Get all Products", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully found all Products", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductEstimationsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "No products found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @GetMapping()
    public BaseResponse<List<ProductEstimationsDTO>> getAllProducts(){
        return BaseResponse.<List<ProductEstimationsDTO>>builder()
                .httpCode(200)
                .message("OK")
                .response(productService.getAllProducts())
                .build();
    }

    @Operation(summary = "Post new Product", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created Product", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @PostMapping()
    public BaseResponse<ProductDTO> postProduct(@RequestBody ProductRequest request){
        return BaseResponse.<ProductDTO>builder()
                .httpCode(201)
                .message("CREATED")
                .response(productService.postProduct(request))
                .build();
    }

    @Operation(summary = "Put Product by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Product", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @PutMapping("/{id}")
    public BaseResponse<ProductDTO> putProduct(@RequestBody ProductRequest request, @PathVariable Long id){
        return BaseResponse.<ProductDTO>builder()
                .httpCode(400)
                .message("NO CONTENT")
                .response(ProductMapper.toDTO(productService.updateProduct(request,id)))
                .build();
    }

    @Operation(summary = "Delete Product by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Product", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
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

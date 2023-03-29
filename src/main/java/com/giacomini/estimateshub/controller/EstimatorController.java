package com.giacomini.estimateshub.controller;


import com.giacomini.estimateshub.dto.EstimatorDTO;
import com.giacomini.estimateshub.dto.EstimatorEstimationsDTO;
import com.giacomini.estimateshub.mapper.EstimatorMapper;
import com.giacomini.estimateshub.request.EstimatorRequest;
import com.giacomini.estimateshub.response.BaseResponse;
import com.giacomini.estimateshub.response.BaseResponseError;
import com.giacomini.estimateshub.service.EstimatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("estimator")
public class EstimatorController {

    private final EstimatorService estimatorService;

    @ApiOperation(
            value = "Get Estimator by id",
            response = EstimatorDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found Estimator"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping("/{id}")
    public BaseResponse<EstimatorDTO> getEstimatorById(@PathVariable Long id){
        return BaseResponse.<EstimatorDTO>builder()
                .httpCode(200)
                .message("OK")
                .response(estimatorService.getById(id))
                .build();
    }

    @ApiOperation(
            value = "Get all Estimators",
            response = EstimatorEstimationsDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found all Estimators"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping()
    public BaseResponse<List<EstimatorDTO>> getAllEstimators(){
        return BaseResponse.<List<EstimatorDTO>>builder()
                .httpCode(200)
                .message("OK")
                .response(estimatorService.getAllEstimators())
                .build();
    }

    @ApiOperation(
            value = "Post new Estimator",
            response = EstimatorDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created Estimator"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PostMapping()
    public BaseResponse<EstimatorDTO> postEstimator(@RequestBody EstimatorRequest request){
        return BaseResponse.<EstimatorDTO>builder()
                .httpCode(201)
                .message("CREATED")
                .response(estimatorService.postEstimator(request))
                .build();
    }

    @ApiOperation(
            value = "Put Estimator by id",
            response = EstimatorDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated Estimator"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PutMapping("/{id}")
    public BaseResponse<EstimatorDTO> putEstimator(@RequestBody EstimatorRequest request, @PathVariable Long id){
        return BaseResponse.<EstimatorDTO>builder()
                .httpCode(400)
                .message("NO CONTENT")
                .response(EstimatorMapper.toDTO(estimatorService.updateEstimator(request, id)))
                .build();
    }

    @ApiOperation(
            value = "Delete Estimator by id",
            response = EstimatorDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted Estimator"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @DeleteMapping("/{id}")
    public BaseResponse<EstimatorDTO> deleteEstimator(@PathVariable("id") Long id){
        estimatorService.deleteEstimatorById(id);
        return BaseResponse.<EstimatorDTO>builder()
                .httpCode(202)
                .message("ACCEPTED")
                .build();
    }
}

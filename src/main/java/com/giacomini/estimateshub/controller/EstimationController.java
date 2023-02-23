package com.giacomini.estimateshub.controller;


import com.giacomini.estimateshub.dto.EstimationDTO;
import com.giacomini.estimateshub.mapper.EstimationMapper;
import com.giacomini.estimateshub.request.EstimationRequest;
import com.giacomini.estimateshub.response.BaseResponse;
import com.giacomini.estimateshub.response.BaseResponseError;
import com.giacomini.estimateshub.service.EstimationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("estimation")
public class EstimationController {

    private final EstimationService estimationService;

    @ApiOperation(
            value = "Get Estimation by id",
            response = EstimationDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found Estimation"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping("/{id}")
    public BaseResponse<EstimationDTO> getEstimationById(@PathVariable Long id){
        return BaseResponse.<EstimationDTO>builder()
                .httpCode(200)
                .message("OK")
                .response(estimationService.getById(id))
                .build();
    }

    @ApiOperation(
            value = "Get all Estimations",
            response = EstimationDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found all Estimations"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping()
    public BaseResponse<List<EstimationDTO>> getAllEstimations(){
        return BaseResponse.<List<EstimationDTO>>builder()
                .httpCode(200)
                .message("OK")
                .response(estimationService.getAllEstimations())
                .build();
    }

    @ApiOperation(
            value = "Post new Estimation",
            response = EstimationDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created Estimation"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PostMapping()
    public BaseResponse<EstimationDTO> postEstimation(@RequestBody EstimationRequest request){
        return BaseResponse.<EstimationDTO>builder()
                .httpCode(201)
                .message("CREATED")
                .response(estimationService.postEstimation(request))
                .build();
    }

    @ApiOperation(
            value = "Put Estimation by id",
            response = EstimationDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated Estimation"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PutMapping("/{id}")
    public BaseResponse<EstimationDTO> putEstimation(@RequestBody EstimationRequest request, @PathVariable Long id){
        return BaseResponse.<EstimationDTO>builder()
                .httpCode(400)
                .message("NO CONTENT")
                .response(EstimationMapper.toDTO(estimationService.updateEstimation(request,id)))
                .build();
    }

    @ApiOperation(
            value = "Delete Estimation by id",
            response = EstimationDTO.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted Estimation"),
            @ApiResponse(code = 400, message = "Bad Request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Order not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @DeleteMapping("/{id}")
    public BaseResponse<EstimationDTO> deleteEstimation(@PathVariable("id") Long id){
        estimationService.deleteEstimationById(id);
        return BaseResponse.<EstimationDTO>builder()
                .httpCode(202)
                .message("ACCEPTED")
                .build();
    }
}

package com.giacomini.estimateshub.controller;


import com.giacomini.estimateshub.dto.EstimatorDTO;
import com.giacomini.estimateshub.dto.EstimatorEstimationsDTO;
import com.giacomini.estimateshub.mapper.EstimatorMapper;
import com.giacomini.estimateshub.request.EstimatorRequest;
import com.giacomini.estimateshub.response.BaseResponse;
import com.giacomini.estimateshub.response.BaseResponseError;
import com.giacomini.estimateshub.service.EstimatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("estimator")
public class EstimatorController {

    private final EstimatorService estimatorService;

    @Operation(summary = "Get Estimator by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully found Estimator", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimatorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Estimator not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @GetMapping("/{id}")
    public BaseResponse<EstimatorDTO> getEstimatorById(@PathVariable Long id){
        return BaseResponse.<EstimatorDTO>builder()
                .httpCode(200)
                .message("OK")
                .response(estimatorService.getById(id))
                .build();
    }

    @Operation(summary = "Get all Estimators", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully found all Estimators", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimatorEstimationsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "No estimators found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @GetMapping()
    public BaseResponse<List<EstimatorDTO>> getAllEstimators(){
        return BaseResponse.<List<EstimatorDTO>>builder()
                .httpCode(200)
                .message("OK")
                .response(estimatorService.getAllEstimators())
                .build();
    }

    @Operation(summary = "Post new Estimator", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created Estimator", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimatorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @PostMapping()
    public BaseResponse<EstimatorDTO> postEstimator(@RequestBody EstimatorRequest request){
        return BaseResponse.<EstimatorDTO>builder()
                .httpCode(201)
                .message("CREATED")
                .response(estimatorService.postEstimator(request))
                .build();
    }

    @Operation(summary = "Put Estimator by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Estimator", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimatorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Estimator not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @PutMapping("/{id}")
    public BaseResponse<EstimatorDTO> putEstimator(@RequestBody EstimatorRequest request, @PathVariable Long id){
        return BaseResponse.<EstimatorDTO>builder()
                .httpCode(400)
                .message("NO CONTENT")
                .response(EstimatorMapper.toDTO(estimatorService.updateEstimator(request, id)))
                .build();
    }

    @Operation(summary = "Delete Estimator by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Estimator", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimatorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Estimator not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
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

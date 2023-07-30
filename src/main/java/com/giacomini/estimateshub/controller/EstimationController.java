package com.giacomini.estimateshub.controller;


import com.giacomini.estimateshub.dto.EstimationDTO;
import com.giacomini.estimateshub.mapper.EstimationMapper;
import com.giacomini.estimateshub.request.EstimationRequest;
import com.giacomini.estimateshub.response.BaseResponse;
import com.giacomini.estimateshub.response.BaseResponseError;
import com.giacomini.estimateshub.service.EstimationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("estimation")
public class EstimationController {

    private final EstimationService estimationService;

    @Operation(summary = "Get Estimation by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully found Estimation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Estimation not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @GetMapping("/{id}")
    public BaseResponse<EstimationDTO> getEstimationById(@PathVariable Long id){
        return BaseResponse.<EstimationDTO>builder()
                .httpCode(200)
                .message("OK")
                .response(estimationService.getById(id))
                .build();
    }

    @Operation(summary = "Get all Estimations", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully found all Estimations", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "No estimations found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @GetMapping()
    public BaseResponse<List<EstimationDTO>> getAllEstimations(){
        return BaseResponse.<List<EstimationDTO>>builder()
                .httpCode(200)
                .message("OK")
                .response(estimationService.getAllEstimations())
                .build();
    }

    @Operation(summary = "Post new Estimation", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created Estimation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @PostMapping()
    public BaseResponse<EstimationDTO> postEstimation(@RequestBody EstimationRequest request){
        return BaseResponse.<EstimationDTO>builder()
                .httpCode(201)
                .message("CREATED")
                .response(estimationService.postEstimation(request))
                .build();
    }

    @Operation(summary = "Put Estimation Status by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Estimation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Estimation not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
    })
    @PutMapping("/{id}")
    public BaseResponse<EstimationDTO> putEstimation(@RequestBody EstimationRequest request, @PathVariable Long id){
        return BaseResponse.<EstimationDTO>builder()
                .httpCode(400)
                .message("NO CONTENT")
                .response(EstimationMapper.toDTO(estimationService.updateEstimation(request,id)))
                .build();
    }

    @Operation(summary = "Delete Estimation by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Estimation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstimationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Estimation not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseError.class)))
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

package vn.unigap.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.Seeker.SeekerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.service.seeker.SeekerService;
import vn.unigap.common.controller.AbstractResponseController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping(value = "/seekers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Seeker", description = "Seekers management")
@SecurityRequirement(name = "Authorization")
public class SeekerController extends AbstractResponseController{
    private final SeekerService seekerService;

    private static final Logger logger = LogManager.getLogger(EmployerController.class);
    @Autowired
    public SeekerController( SeekerService seekerService){
        this.seekerService = seekerService;
    }

    @Operation(summary = "Get all seekers", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = SeekerController.ResponsePageSeeker.class))) })
    @GetMapping(value="", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> list( @Valid PageDtoIn pageDtoIn){
        return responseEntity(() -> {
            return this.seekerService.list(pageDtoIn);}
        );
    }

    @Operation(summary = "Get seeker by Id", responses = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SeekerController.ResponseSeeker.class)) }) })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getSeekerById(@PathVariable(value = "id") Long id) {
        logger.info("Received request to get seeker with ID: {}", id);
        try {
            SeekerDtoOut seeker = this.seekerService.getSeekerById(id);
            if (seeker != null) {
                logger.info("Successfully retrieved seeker with ID: {}", id);
                return ResponseEntity.ok(seeker);
            } else {
                logger.warn("seeker with ID: {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("seeker not found");
            }
        } catch (Exception e) {
            logger.error("Error retrieving seeker with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @Operation(summary = "Create new seeker", responses = { @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SeekerController.ResponseSeeker.class)) }) })
    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid SeekerDtoIn seekerDtoIn) {
        return responseEntity(() -> {
            return this.seekerService.create(seekerDtoIn);
        }, HttpStatus.CREATED);
    }

    @Operation(summary = "Update seeker", responses = {@ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SeekerController.ResponseSeeker.class))
    })})
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,
                                    @RequestBody @Valid SeekerDtoIn seekerDtoIn) {
        return responseEntity(() -> {
            return this.seekerService.update(id, seekerDtoIn);
        });
    }

    @Operation(summary = "Delete seeker", responses = {@ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = vn.unigap.common.response.ApiResponse.class))
    })})
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteSeeker(@PathVariable(value = "id") Long id){
        return responseEntity(() -> {
            this.seekerService.deleteSeeker(id);
            return new HashMap<>();
        });
    }

    private static class ResponseSeeker extends vn.unigap.common.response.ApiResponse<SeekerDtoOut> {
    }

    private static class ResponsePageSeeker extends vn.unigap.common.response.ApiResponse<PageDtoOut<SeekerDtoOut>> {
    }
}

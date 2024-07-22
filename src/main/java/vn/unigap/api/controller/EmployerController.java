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
import vn.unigap.api.dto.in.Employer.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.Employer.EmployerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.service.employer.EmployerService;
import vn.unigap.common.controller.AbstractResponseController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping(value = "/employers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employer", description = "Employers management")
public class EmployerController extends AbstractResponseController {
    private final EmployerService employerService;

    private static final Logger logger = LogManager.getLogger(EmployerController.class);

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @Operation(summary = "Get all employers", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponsePageEmployer.class))) })
    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    // Add " ?page= X" to show the X page.
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn) {
        return responseEntity(() -> {
            return this.employerService.list(pageDtoIn);
        });
    }

    @Operation(summary = "Get Employer by Id", responses = { @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEmployer.class)) }) })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getEmployerById(@PathVariable(value = "id") Long id) {
        logger.info("Received request to get employer with ID: {}", id);
        try {
            EmployerDtoOut employer = this.employerService.getEmployerById(id);
            if (employer != null) {
                logger.info("Successfully retrieved employer with ID: {}", id);
                return ResponseEntity.ok(employer);
            } else {
                logger.warn("employer with ID: {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employer not found");
            }
        } catch (Exception e) {
            logger.error("Error retrieving employer with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @Operation(summary = "Create new employer", responses = { @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEmployer.class)) }) })
    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid EmployerDtoIn employerDtoIn) {
        return responseEntity(() -> {
            return this.employerService.create(employerDtoIn);
        }, HttpStatus.CREATED);
    }

    @Operation(summary = "Update employer", responses = { @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEmployer.class)) }) })
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,
            @RequestBody @Valid UpdateEmployerDtoIn updateEmployerDtoIn) {
        return responseEntity(() -> {
            return this.employerService.update(id, updateEmployerDtoIn);
        });
    }

    @Operation(summary = "Delete employer", responses = { @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = vn.unigap.common.response.ApiResponse.class)) }) })
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteEmployer(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            this.employerService.deleteEmployer(id);
            return new HashMap<>();
        });
    }

    private static class ResponseEmployer extends vn.unigap.common.response.ApiResponse<EmployerDtoOut> {
    }

    private static class ResponsePageEmployer
            extends vn.unigap.common.response.ApiResponse<PageDtoOut<EmployerDtoOut>> {
    }

}
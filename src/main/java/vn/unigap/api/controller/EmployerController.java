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
import vn.unigap.api.dto.in.UpdateEmployerDtoIn;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.service.employer.EmployerService;
import vn.unigap.common.controller.AbstractResponseController;

@RestController
@RequestMapping("/employers")
public class EmployerController extends AbstractResponseController {
    private final EmployerService employerService;
    @Autowired
    public EmployerController( EmployerService employerService){
        this.employerService = employerService;
    }

    @Operation(summary = "Get all users", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponsePageEmployer.class))) })
    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn){
        return responseEntity(() -> {
            return this.employerService.list(pageDtoIn);
        });
    }
//    @GetMapping("/{employerId}")
//    public ResponseEntity<Object> getEmployer(@PathVariable long employerId){
//
//    }
//    @PostMapping
//    public ResponseEntity<Object> createEmployer(@RequestBody @Valid EmployerCreationRequest request){
//
//    }
//
//    @PutMapping("/{employerId}")
//    public ResponseEntity<Object> updateEmployer(@PathVariable long employerId, @RequestBody @Valid EmployerUpdateRequest request){
//
//    }
//    @DeleteMapping("/{employerId}")
//    String deleteEmployer(@PathVariable long employerId){
//
//    }

    private static class ResponseEmployer extends vn.unigap.common.response.ApiResponse<EmployerDtoOut> {
    }

    private static class ResponsePageEmployer extends vn.unigap.common.response.ApiResponse<PageDtoOut<EmployerDtoOut>> {
    }

}
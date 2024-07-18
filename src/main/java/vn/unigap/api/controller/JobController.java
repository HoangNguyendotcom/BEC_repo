package vn.unigap.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import vn.unigap.api.dto.in.Job.UpdateJobDtoIn;
import vn.unigap.api.dto.in.Job.JobDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.JobDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.service.job.JobService;
import vn.unigap.common.controller.AbstractResponseController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/jobs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Job", description = "List of Jobs")
public class JobController  extends AbstractResponseController{
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService){ this.jobService = jobService; }

    @Operation(summary = "Get all of jobs", responses = {@ApiResponse(responseCode = "200",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponsePageJob.class))})})
    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn){
        return responseEntity(() -> {
            return this.jobService.list(pageDtoIn);
        });
    }

    @Operation(summary = "Get job by Id", responses = {@ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JobController.ResponseJob.class)) }) })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getJobById(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            return this.jobService.getJobById(id);
        });
    }

    @Operation(summary = "Create new job", responses = { @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = JobController.ResponseJob.class)) }) })
    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid JobDtoIn jobDtoIn) {
        return responseEntity(() -> {
            return this.jobService.create(jobDtoIn);
        }, HttpStatus.CREATED);
    }

    @Operation(summary = "Update job", responses = {@ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = JobController.ResponseJob.class))
    })})
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,
                                    @RequestBody @Valid UpdateJobDtoIn updateJobDtoIn) {
        return responseEntity(() -> {
            return this.jobService.update(id, updateJobDtoIn);
        });
    }

    @Operation(summary = "Delete job", responses = {@ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = vn.unigap.common.response.ApiResponse.class))
    })})
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteJob(@PathVariable(value = "id") Long id){
        return responseEntity(() -> {
            this.jobService.deleteJob(id);
            return new HashMap<>();
        });
    }

    private static class ResponseJob extends vn.unigap.common.response.ApiResponse<JobDtoOut>{

    }

    private static class ResponsePageJob extends vn.unigap.common.response.ApiResponse<PageDtoOut<JobDtoOut>>{

    }
}

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
import vn.unigap.api.dto.in.Resume.ResumeDtoIn;
import vn.unigap.api.dto.in.Resume.UpdateResumeDtoIn;
import vn.unigap.api.dto.in.Seeker.SeekerDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.JobDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.ResumeDtoOut;
import vn.unigap.api.service.employer.EmployerService;
import vn.unigap.api.service.job.JobService;
import vn.unigap.api.service.resume.ResumeService;
import vn.unigap.common.controller.AbstractResponseController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/resumes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Resume", description = "List of Resumes")
public class ResumeController extends AbstractResponseController{
    private final ResumeService resumeService;
    @Autowired
    public ResumeController( ResumeService resumeService){
        this.resumeService = resumeService;
    }

    @Operation(summary = "Get all resumes", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ResumeController.ResponsePageResume.class))) })
    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    // Add " ?page= X" to show the X page.
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn){
        return responseEntity(() -> {
            return this.resumeService.list(pageDtoIn);
        });
    }
    @Operation(summary = "Get resume by Id", responses = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResumeController.ResponseResume.class)) }) })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getResumeById(@PathVariable(value = "id") Long id) {
        return responseEntity(() -> {
            return this.resumeService.getResumeById(id);
        });
    }
    @Operation(summary = "Create new resume", responses = { @ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResumeController.ResponseResume.class)) }) })
    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid ResumeDtoIn resumeDtoIn) {
        return responseEntity(() -> {
            return this.resumeService.create(resumeDtoIn);
        }, HttpStatus.CREATED);
    }

    @Operation(summary = "Update resume", responses = {@ApiResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResumeController.ResponseResume.class))
    })})
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,
                                   @RequestBody @Valid UpdateResumeDtoIn updateResumeDtoIn){
        return responseEntity(() -> {
            return this.resumeService.update( id, updateResumeDtoIn);
        });
    }

    @Operation(summary = "Delete resume", responses = {@ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = vn.unigap.common.response.ApiResponse.class))
    })})
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> deleteResume(@PathVariable(value = "id") Long id){
        return responseEntity(() -> {
            this.resumeService.deleteResume(id);
            return new HashMap<>();
        });
    }
    private static class ResponseResume extends vn.unigap.common.response.ApiResponse<ResumeDtoOut> {
    }

    private static class ResponsePageResume extends vn.unigap.common.response.ApiResponse<PageDtoOut<ResumeDtoOut>> {
    }
}

package vn.unigap.api.controller;

import java.util.Date;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.out.StatisticsDtoOut;
import vn.unigap.api.service.statistics.StatisticsService;
import vn.unigap.common.controller.AbstractResponseController;

@RestController
@RequestMapping("/statistics")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Statistics", description = "Total new items")
public class StatisticsController extends AbstractResponseController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Operation(summary = "Statistics", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponseStatistics.class))) })
    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> statistics(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return responseEntity(() -> {
            return this.statisticsService.statistics(fromDate, toDate);
        });
    }

    private static class ResponseStatistics extends vn.unigap.common.response.ApiResponse<StatisticsDtoOut> {
    }

}
package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsDtoOut {
    private Integer numEmployers;
    private Integer numJobs;
    private Integer numSeekers;
    private Integer numResumes;
    private List<AnalysisPerDayDto> chart;

    @Data
    @Builder
    public static class AnalysisPerDayDto {
        private Date date;
        private Integer numEmployer;
        private Integer numJob;
        private Integer numSeeker;
        private Integer numResume;
    }
}
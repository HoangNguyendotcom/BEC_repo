package vn.unigap.api.service.statistics;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.out.StatisticsDtoOut;
import vn.unigap.api.repository.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final EmployerRepository employerRepository;
    private final JobRepository jobRepository;
    private final SeekerRepository seekerRepository;
    private final ResumeRepository resumeRepository;

    @Autowired
    public StatisticsServiceImpl(EmployerRepository employerRepository,
                                 JobRepository jobRepository,
                                 SeekerRepository seekerRepository,
                                 ResumeRepository resumeRepository) {
        this.employerRepository = employerRepository;
        this.jobRepository = jobRepository;
        this.seekerRepository = seekerRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    public StatisticsDtoOut statistics(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime start = fromDate.atStartOfDay();
        LocalDateTime end = toDate.atStartOfDay().plusDays(1);
        Integer numEmployers = employerRepository.countByCreatedAtBetween(start, end);
        Integer numJobs = jobRepository.countByCreatedAtBetween(start, end);
        Integer numSeekers = seekerRepository.countByCreatedAtBetween(start, end);
        Integer numResumes = resumeRepository.countByCreatedAtBetween(start, end);
        List<StatisticsDtoOut.AnalysisPerDayDto> chart = new ArrayList<>();

        while(!fromDate.isAfter(toDate)){
            LocalDateTime startOfDay = fromDate.atStartOfDay();
            LocalDateTime endOfDay = fromDate.atStartOfDay().plusDays(1);
            Integer numEmployer = employerRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            Integer numJob = jobRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            Integer numSeeker = seekerRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            Integer numResume = resumeRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            StatisticsDtoOut.AnalysisPerDayDto perDayDto = StatisticsDtoOut.AnalysisPerDayDto.builder()
                    .date(fromDate)
                    .numEmployer(numEmployer)
                    .numJob(numJob)
                    .numSeeker(numSeeker)
                    .numResume(numResume)
                    .build();
            chart.add(perDayDto);
            fromDate = fromDate.plusDays(1);
        }
        return StatisticsDtoOut.builder()
                .numEmployers(numEmployers)
                .numJobs(numJobs)
                .numSeekers(numSeekers)
                .numResumes(numResumes)
                .chart(chart)
                .build();
    }
}
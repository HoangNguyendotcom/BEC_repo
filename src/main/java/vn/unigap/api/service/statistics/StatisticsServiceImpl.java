package vn.unigap.api.service.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.out.StatisticsDtoOut;
import vn.unigap.api.repository.jpa.EmployerRepository;
import vn.unigap.api.repository.jpa.JobRepository;
import vn.unigap.api.repository.jpa.ResumeRepository;
import vn.unigap.api.repository.jpa.SeekerRepository;

import static org.apache.commons.lang3.time.DateUtils.addDays;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final EmployerRepository employerRepository;
    private final JobRepository jobRepository;
    private final SeekerRepository seekerRepository;
    private final ResumeRepository resumeRepository;

    @Autowired
    public StatisticsServiceImpl(EmployerRepository employerRepository, JobRepository jobRepository,
            SeekerRepository seekerRepository, ResumeRepository resumeRepository) {
        this.employerRepository = employerRepository;
        this.jobRepository = jobRepository;
        this.seekerRepository = seekerRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    public StatisticsDtoOut statistics(Date fromDate, Date toDate) {
        Date start = fromDate;
        Date end = toDate;
        Integer numEmployers = employerRepository.countByCreatedAtBetween(start, end);
        Integer numJobs = jobRepository.countByCreatedAtBetween(start, end);
        Integer numSeekers = seekerRepository.countByCreatedAtBetween(start, end);
        Integer numResumes = resumeRepository.countByCreatedAtBetween(start, end);
        List<StatisticsDtoOut.AnalysisPerDayDto> chart = new ArrayList<>();

        while (!fromDate.after(toDate)) {
            Date startOfDay = fromDate;
            Date endOfDay = toDate;
            Integer numEmployer = employerRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            Integer numJob = jobRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            Integer numSeeker = seekerRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            Integer numResume = resumeRepository.countByCreatedAtBetween(startOfDay, endOfDay);
            StatisticsDtoOut.AnalysisPerDayDto perDayDto = StatisticsDtoOut.AnalysisPerDayDto.builder().date(fromDate)
                    .numEmployer(numEmployer).numJob(numJob).numSeeker(numSeeker).numResume(numResume).build();
            chart.add(perDayDto);
            fromDate = addDays(fromDate, 1);
            ;
        }
        return StatisticsDtoOut.builder().numEmployers(numEmployers).numJobs(numJobs).numSeekers(numSeekers)
                .numResumes(numResumes).chart(chart).build();
    }
}
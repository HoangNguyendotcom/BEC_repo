package vn.unigap.api.service.statistics;

import vn.unigap.api.dto.out.StatisticsDtoOut;

import java.time.LocalDate;

public interface StatisticsService {
    StatisticsDtoOut statistics(LocalDate fromDate, LocalDate toDate);
}

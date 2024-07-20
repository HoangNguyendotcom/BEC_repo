package vn.unigap.api.service.statistics;

import vn.unigap.api.dto.out.StatisticsDtoOut;

import java.util.Date;

public interface StatisticsService {
    StatisticsDtoOut statistics(Date fromDate, Date toDate);
}

package com.example.unigap.api.service.analytic;

import com.example.unigap.api.dto.out.analytic.Analytic;

import java.util.Date;

public interface AnalyticService {
  Analytic getAnalyticBetweenDates(Date fromDate, Date toDate);
}

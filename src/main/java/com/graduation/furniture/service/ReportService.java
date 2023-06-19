package com.graduation.furniture.service;

import com.graduation.furniture.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<RevenueDto> revenueByPeriodTime(LocalDate startDate, LocalDate endDate);
    List<OrderDto> orderByPeriodTime(LocalDate startDate, LocalDate endDate);
    String totalRevenueByPeriod(LocalDate startDate, LocalDate endDate);

    String totalOrderByPeriod(LocalDate startDate, LocalDate endDate);

    String totalCustomerByPeriod(LocalDate startDate, LocalDate endDate);

    String totalNewUserByPeriod(LocalDate startDate, LocalDate endDate);
    List<CategoryReportDto> topCategoriesByPeriod(LocalDate startDate, LocalDate endDate);
    List<TopProductReportDto> topProductByPeriod(LocalDate startDate, LocalDate endDate);
    List<TopUserReportDto> topUserByPeriod(LocalDate startDate, LocalDate endDate);
    List<OrderRecentReportDto> orderRecentByPeriod(LocalDate startDate, LocalDate endDate);
    List<TopProductTrendingDto> trendingProductByPeriod(LocalDate startDate, LocalDate endDate);
}

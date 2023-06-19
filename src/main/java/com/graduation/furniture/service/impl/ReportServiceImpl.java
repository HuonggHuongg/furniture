package com.graduation.furniture.service.impl;

import com.graduation.furniture.dto.*;
import com.graduation.furniture.repository.ReportRepo;
import com.graduation.furniture.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepo reportRepo;
    @Override
    public List<RevenueDto> revenueByPeriodTime(LocalDate startDate, LocalDate endDate) {
        return reportRepo.revenueByPeriodTime(startDate, endDate);
    }

    @Override
    public List<OrderDto> orderByPeriodTime(LocalDate startDate, LocalDate endDate) {
        return reportRepo.totalOrderByPeriodTime(startDate, endDate);
    }

    @Override
    public String totalRevenueByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.totalRevenueByPeriod(startDate, endDate);
    }

    @Override
    public String totalOrderByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.totalOrderByPeriod(startDate, endDate);
    }

    @Override
    public String totalCustomerByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.totalCustomerByPeriod(startDate, endDate);
    }

    @Override
    public String totalNewUserByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.totalNewUserByPeriod(startDate, endDate);
    }

    @Override
    public List<CategoryReportDto> topCategoriesByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.topCategoriesByPeriod(startDate, endDate);
    }

    @Override
    public List<TopProductReportDto> topProductByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.topProductByPeriod(startDate,endDate);
    }

    @Override
    public List<TopUserReportDto> topUserByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.topUserByPeriod(startDate, endDate);
    }

    @Override
    public List<OrderRecentReportDto> orderRecentByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.orderRecentByPeriod(startDate,endDate);
    }

    @Override
    public List<TopProductTrendingDto> trendingProductByPeriod(LocalDate startDate, LocalDate endDate) {
        return reportRepo.trendingProductByPeriod(startDate,endDate);
    }
}

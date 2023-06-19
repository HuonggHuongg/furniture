package com.graduation.furniture.controller;

import com.graduation.furniture.dto.*;
import com.graduation.furniture.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("revenue")
    public ResponseEntity<?> revenueByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                 @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<RevenueDto> revenueDtoList = reportService.revenueByPeriodTime(startDate, endDate);
        return new ResponseEntity<>(revenueDtoList, HttpStatus.OK);
    }

    @GetMapping("order")
    public ResponseEntity<?> orderByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                               @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<OrderDto> orderDtoList = reportService.orderByPeriodTime(startDate, endDate);
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("category")
    public ResponseEntity<?> topCategoryByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                     @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<CategoryReportDto> categoryReportDtoList = reportService.topCategoriesByPeriod(startDate, endDate);
        return new ResponseEntity<>(categoryReportDtoList, HttpStatus.OK);
    }

    @GetMapping("product")
    public ResponseEntity<?> topProductByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                    @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<TopProductReportDto> topProductReportDtoList = reportService.topProductByPeriod(startDate, endDate);
        return new ResponseEntity<>(topProductReportDtoList, HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<?> topUserByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                 @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<TopUserReportDto> topUserReportDtoList = reportService.topUserByPeriod(startDate, endDate);
        return new ResponseEntity<>(topUserReportDtoList, HttpStatus.OK);
    }

    @GetMapping("order-recent")
    public ResponseEntity<?> orderRecentByPeriodTime(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                     @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<OrderRecentReportDto> orderRecentReportDtoList = reportService.orderRecentByPeriod(startDate, endDate);
        return new ResponseEntity<>(orderRecentReportDtoList, HttpStatus.OK);
    }

    @GetMapping("summary")
    public ResponseEntity<?> summary(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                     @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate previousStartDate = startDate.minusDays(daysDifference);
        LocalDate previousEndDate = startDate.minusDays(1);
        String totalRevenue = reportService.totalRevenueByPeriod(startDate, endDate);
        String totalOrder = reportService.totalOrderByPeriod(startDate, endDate);
        String totalCustomer = reportService.totalCustomerByPeriod(startDate, endDate);
        String totalNewUser = reportService.totalNewUserByPeriod(startDate, endDate);

        String totalRevenuePrevious = reportService.totalRevenueByPeriod(previousStartDate, previousEndDate);
        String totalOrderPrevious = reportService.totalOrderByPeriod(previousStartDate, previousEndDate);
        String totalCustomerPrevious = reportService.totalCustomerByPeriod(previousStartDate, previousEndDate);
        String totalNewUserPrevious = reportService.totalNewUserByPeriod(previousStartDate, previousEndDate);

        Double revenueChangePercent = (Double.parseDouble(totalRevenue) / Double.parseDouble(totalRevenuePrevious) * 100) - 100;
        Double orderChangePercent = (Double.parseDouble(totalOrder) / Double.parseDouble(totalOrderPrevious) * 100) - 100;
        Double customerChangePercent = (Double.parseDouble(totalCustomer) / Double.parseDouble(totalCustomerPrevious) * 100) - 100;
        Double newUserChangePercent = (Double.parseDouble(totalNewUser) / Double.parseDouble(totalNewUserPrevious) * 100) - 100;

        SummaryResponse summaryResponse = new SummaryResponse(totalRevenue, totalOrder, totalCustomer, totalNewUser, revenueChangePercent, orderChangePercent, customerChangePercent, newUserChangePercent);

        return new ResponseEntity<>(summaryResponse, HttpStatus.OK);
    }

}

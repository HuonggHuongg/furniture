package com.graduation.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryResponse {
    private String totalRevenue;
    private String totalOrder;
    private String totalCustomer;
    private String totalNewUser;
    private Double revenueChangePercent;
    private Double orderChangePercent;
    private Double customerChangePercent;
    private Double newUserChangePercent;
}

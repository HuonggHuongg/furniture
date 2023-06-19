package com.graduation.furniture.dto;

import com.graduation.furniture.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailPaymentSuccess {
    private String receiver;
    private Integer orderId;
    private String createdAt;
    private String subject;
    private String email;
    private List<Integer> orderItemIdList;
    private String total;
}

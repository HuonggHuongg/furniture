package com.graduation.furniture.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.graduation.furniture.entities.OrderUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class StatusOrderDTO {

    private Integer statusId;

    private String statusName;

    private Set<OrderUser> orderUsers;
}

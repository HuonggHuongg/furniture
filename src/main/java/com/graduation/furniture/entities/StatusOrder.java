package com.graduation.furniture.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"orderUsers"})
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    @Column(columnDefinition = "varchar(255)")
    private String statusName;

    @OneToMany(mappedBy = "statusOrder")
    private Set<OrderUser> orderUsers;
}

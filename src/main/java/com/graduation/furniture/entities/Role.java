package com.graduation.furniture.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"userRoles"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(columnDefinition = "varchar(50)")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;
}

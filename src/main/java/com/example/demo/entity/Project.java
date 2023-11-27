package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_project")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Role cannot be null")
    private String name;

    @NotNull(message = "AccountId cannot be null")
    private Integer accountId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Date startDate;

    private  Date endDate;

    private Integer createdBy;

    private Date updatedAt;

    private String status;

    private String note;

    private String description;

    public Project(String errorMessage) {
        this.name = errorMessage;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}

package com.zied.bankingApp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(
            name = "createdDated",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdDated;

    @LastModifiedDate
    @Column(
            name = "lastModifiedDate",
            nullable = false,
            updatable = false
    )
    private LocalDateTime lastModifiedDate;
}

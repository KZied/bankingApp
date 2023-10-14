package com.zied.bankingApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Address extends AbstractEntity {

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

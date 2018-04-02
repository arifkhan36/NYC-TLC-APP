package com.example.driversapi.models;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "DRIVERS")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "license_number")
    private String license_number;

    @Column(name = "type")
    private String type;

    @Column(name = "expiration_date")
    private String expiration_date;

    @Column(name = "last_updated_date")
    private String last_updated_date;

    @Column(name = "last_updated_time")
    private String last_updated_time;


    public Driver(String name, String licenseNumber, String type, String expirationDate,String last_updated_date,String last_updated_time) {
        this.name = name;
        this.license_number = licenseNumber;
        this.type = type;
        this.last_updated_date = last_updated_date;
        this.last_updated_time = last_updated_time;
        this.expiration_date = expirationDate;
    }
}

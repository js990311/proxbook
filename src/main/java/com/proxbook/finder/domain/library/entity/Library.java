package com.proxbook.finder.domain.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libraries")
public class Library {
    @Id
    @Column(name = "library_id")
    private String id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column
    private String webpage;
}

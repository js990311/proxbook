package com.proxbook.finder.domain.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
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

    @Column(name = "longiude") // sql쓸때 오타가
    private Double longtitude;

    @Column
    private Double latitude;

    @Column
    private String webpage;
}

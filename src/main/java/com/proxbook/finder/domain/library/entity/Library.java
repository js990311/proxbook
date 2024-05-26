package com.proxbook.finder.domain.library.entity;

import com.proxbook.finder.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "libraries")
public class Library extends BaseTimeEntity {
    @Id
    @Column(name = "library_id")
    private Long id;

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

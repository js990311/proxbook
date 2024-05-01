package com.proxbook.finder.domain.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_id")
    private String id;

    @Column
    private String title;

    @Column
    private String publisher;

    @Column(name = "publish_year")
    private Integer publishYear;

}

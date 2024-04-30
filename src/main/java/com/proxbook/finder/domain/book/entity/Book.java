package com.proxbook.finder.domain.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name ="books")
public class Book {
    @Id
    @Column(name = "book_id")
    private String id;

    @Column
    private String title;

    @Column
    private String publisher;

    @Column
    private Integer publish_year;

}

package com.proxbook.finder.domain.book.entity;

import com.proxbook.finder.domain.book.dto.UpdateBookDto;
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

    @Column(name = "book_description")
    private String description;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "book_info_url")
    private String bookInfoUrl;

    void updateBookInfo(UpdateBookDto updateBookDto){
        this.publisher = updateBookDto.getPublisher();
        this.publishYear = updateBookDto.getPublishYear();
        this.description = updateBookDto.getDescription();
        this.thumbnailUrl = updateBookDto.getThumbnailUrl();
        this.bookInfoUrl = updateBookDto.getBookInfoUrl();
    }

    public static Book updateBookInfo(Book book, UpdateBookDto updateBookDto){
        book.updateBookInfo(updateBookDto);
        return book;
    }
}

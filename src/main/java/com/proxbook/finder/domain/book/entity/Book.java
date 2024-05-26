package com.proxbook.finder.domain.book.entity;

import com.proxbook.finder.domain.book.dto.UpdateBookDto;
import com.proxbook.finder.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "books")
public class Book extends BaseTimeEntity {
    @Id
    @Column(name = "book_id")
    private Long id; // isbn

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
    
    public Book updateBookInfo(UpdateBookDto updateBookDto){
        this.publisher = updateBookDto.getPublisher();
        this.publishYear = updateBookDto.getPublishYear();
        this.description = updateBookDto.getDescription();
        this.thumbnailUrl = updateBookDto.getThumbnailUrl();
        this.bookInfoUrl = updateBookDto.getBookInfoUrl();
        return this;
    }

    public String getIsbn(){
        return id.toString();
    }

}

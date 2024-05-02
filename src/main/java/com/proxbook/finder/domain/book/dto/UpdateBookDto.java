package com.proxbook.finder.domain.book.dto;


public abstract class UpdateBookDto {
    public abstract String getTitle();
    public abstract String getPublisher();
    public abstract Integer getPublishYear();
    public abstract String getDescription();
    public abstract String getThumbnailUrl();
    public abstract String getBookInfoUrl();
}

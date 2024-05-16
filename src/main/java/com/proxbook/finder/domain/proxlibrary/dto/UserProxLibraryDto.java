package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;
import lombok.Getter;

import java.util.List;

@Getter
public class UserProxLibraryDto {
    private String url;
    private Double range;
    private Double latitude;
    private Double longitude;
    private BookDto book;
    private int count;
    private List<ProxLibraryDto> libraries;

    public UserProxLibraryDto(String url, List<ProxLibraryDto> libraries, int count, BookDto book, Double latitude, Double longitude, Double range) {
        this.url = url;
        this.libraries = libraries;
        this.count = count;
        this.book = book;
        this.latitude = latitude;
        this.longitude = longitude;
        this.range = range;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String url;
        private List<ProxLibraryDto> libraries;
        private int count;
        private BookDto book;
        private Double latitude;
        private Double longitude;
        private Double range;


        public UserProxLibraryDto build(){
            return new UserProxLibraryDto(url, libraries, count, book, latitude, longitude, range);
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setLibraries(List<ProxLibraryDto> libraries) {
            this.libraries = libraries;
            if(this.libraries != null)
                this.count = libraries.size();
            else
                this.count = 0;
            return this;
        }

        public Builder setBook(BookDto bookDto) {
            this.book = bookDto;
            return this;
        }

        public Builder setBook(Book book) {
            if(book!=null)
                this.book = BookDto.from(book);
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setRange(Double range) {
            this.range = range;
            return this;
        }
    }
}

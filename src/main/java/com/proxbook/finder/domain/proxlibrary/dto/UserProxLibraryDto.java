package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.application.service.utils.Base62ShortenUrlService;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import lombok.Getter;

import java.util.List;

@Getter
public class UserProxLibraryDto {
    private String url;
    private List<ProxLibraryDto> libraries;
    private int count;
    private BookDto bookDto;

    public UserProxLibraryDto(String url, List<ProxLibraryDto> libraries, int count, BookDto bookDto) {
        this.url = url;
        this.libraries = libraries;
        this.count = count;
        this.bookDto = bookDto;
    }

    public static class Builder{
        private String url;
        private List<ProxLibraryDto> libraries;
        private int count;
        private BookDto bookDto;

        public UserProxLibraryDto build(){
            return new UserProxLibraryDto(url, libraries, count, bookDto);
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

        public Builder setBookDto(BookDto bookDto) {
            this.bookDto = bookDto;
            return this;
        }

        public Builder setBookDto(Book book) {
            if(book!=null)
                this.bookDto = new BookDto(book);
            return this;
        }

    }
}

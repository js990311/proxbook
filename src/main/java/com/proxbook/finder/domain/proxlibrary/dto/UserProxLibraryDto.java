package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.application.service.utils.Base62Encoder;
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

    public UserProxLibraryDto(UserProxLibrary userProxLibrary){
        setBookDto(userProxLibrary.getBook());
        this.url = Base62Encoder.encode(userProxLibrary.getId());
        this.libraries = userProxLibrary.getProxLibraries().stream().map(ProxLibraryDto::new).toList();
        if(this.libraries != null)
            this.count = libraries.size();
        else
            this.count = 0;
    }

    public UserProxLibraryDto(String url, List<ProxLibraryDto> libraries, Book book) {
        setBookDto(book);
        this.url = url;
        this.libraries = libraries;
        if(this.libraries != null)
            this.count = libraries.size();
        else
            this.count = 0;
    }

    private void setBookDto(Book book){
        if(book != null)
            this.bookDto = new BookDto(book);
    }
}

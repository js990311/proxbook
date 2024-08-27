package com.proxbook.finder.domain.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException{
    private static String messageFormat = "Book id %d not found";
    private Long id;
    public BookNotFoundException(Long id) {
        super(String.format(messageFormat, id));
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package com.proxbook.finder.domain.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LibraryNotFoundException extends RuntimeException{
    private static String messageFormat = "Library id %d not found";
    private Long id;
    public LibraryNotFoundException(Long id) {
        super(String.format(messageFormat, id));
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

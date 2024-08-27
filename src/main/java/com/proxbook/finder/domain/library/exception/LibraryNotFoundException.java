package com.proxbook.finder.domain.library.exception;

public class LibraryNotFoundException extends RuntimeException{
    private static String messageFormat = "Library id %d not found";
    public LibraryNotFoundException(Long id) {
        super(String.format(messageFormat, id));
    }
}

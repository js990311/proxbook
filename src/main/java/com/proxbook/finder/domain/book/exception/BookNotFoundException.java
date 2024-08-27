package com.proxbook.finder.domain.book.exception;

public class BookNotFoundException extends RuntimeException{
    private static String messageFormat = "Book id %d not found";
    public BookNotFoundException(Long id) {
        super(String.format(messageFormat, id));
    }

}

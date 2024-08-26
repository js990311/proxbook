package com.proxbook.finder.domain.book.service.update.exception;


public class BookEmptyDataException extends RuntimeException{
    private static final String DEFAULT_MSG = "Not a single data arrived from book update source. ISBN IS ";

    public BookEmptyDataException(String msg) {
        super(msg);
    }

    public static BookEmptyDataException from(String isbn){
        String msg = new StringBuilder(DEFAULT_MSG).append(isbn).toString();
        return new BookEmptyDataException(msg);
    }
}

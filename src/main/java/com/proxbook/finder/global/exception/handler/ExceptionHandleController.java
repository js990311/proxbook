package com.proxbook.finder.global.exception.handler;

import com.proxbook.finder.domain.book.exception.BookNotFoundException;
import com.proxbook.finder.domain.library.exception.LibraryNotFoundException;
import com.proxbook.finder.global.response.ResourceNotFoundExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.proxbook.finder.global.response.ResourceNotFoundExceptionResponse.ResourceType;

@RestControllerAdvice
public class ExceptionHandleController {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ResourceNotFoundExceptionResponse bookNotFoundException(BookNotFoundException e){
        return new ResourceNotFoundExceptionResponse(
                e.getId(), ResourceType.BOOK,e.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(LibraryNotFoundException.class)
    public ResourceNotFoundExceptionResponse libraryNotFoundException(LibraryNotFoundException e){
        return new ResourceNotFoundExceptionResponse(
                e.getId(), ResourceType.LIBRARY,e.getMessage()
        );
    }
}

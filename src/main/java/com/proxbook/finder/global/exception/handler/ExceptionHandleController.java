package com.proxbook.finder.global.exception.handler;

import com.proxbook.finder.domain.book.exception.BookNotFoundException;
import com.proxbook.finder.domain.library.exception.LibraryNotFoundException;
import com.proxbook.finder.global.response.BaseMeta;
import com.proxbook.finder.global.response.BaseResponse;
import com.proxbook.finder.global.response.ResourceNotFoundExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.proxbook.finder.global.response.ResourceNotFoundExceptionResponse.ResourceType;

@RestControllerAdvice
public class ExceptionHandleController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public BaseResponse<ResourceNotFoundExceptionResponse> bookNotFoundException(BookNotFoundException e){
        return new BaseResponse<>(
                HttpStatus.NOT_FOUND,
                new ResourceNotFoundExceptionResponse(
                        e.getId(), ResourceType.BOOK,e.getMessage()
                )
        );
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(LibraryNotFoundException.class)
    public BaseResponse<ResourceNotFoundExceptionResponse> libraryNotFoundException(LibraryNotFoundException e){
        return new BaseResponse<>(
                HttpStatus.NOT_FOUND,
                new ResourceNotFoundExceptionResponse(
                        e.getId(), ResourceType.LIBRARY,e.getMessage()
                )
        );
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<String> serverException(BookNotFoundException e){
        return new BaseResponse<>(
                new BaseMeta(500, "서버에 에러가 발생했습니다. 운영자에게 문의하십시오"),
                "서버에 에러가 발생했습니다. 운영자에게 문의하십시오"
        );
    }

}

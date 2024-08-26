package com.proxbook.finder.domain.book.api;

import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.service.BookService;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import com.proxbook.finder.global.response.BaseListResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/book")
@RestController
public class BookApiController {
    private final BookService bookService;
    private final LibraryService libraryService;
    private final UserProxLibraryService userProxLibraryService;

    @Operation(summary = "책 제목으로 검색")
    @GetMapping()
    public BaseListResponse<BookDto> searchBookByName(@RequestParam(value = "title", required = false) String title){
        return new BaseListResponse<>(bookService.readBookByTitle(title));
    }

    @Operation(summary = "책의 ISBN으로 검색")
    @GetMapping("/{id}")
    public BookDto getBookByBookId(@PathVariable("id") Long id){
        return bookService.readBookByBookId(id);
    }

    @Operation(summary = "이 책을 소장하는 도서관 검색")
    @GetMapping("/{id}/library")
    public BaseListResponse<LibraryDto> getLibraryByBookId(@PathVariable("id") Long id){
        return new BaseListResponse<>(libraryService.readLibraryByBookId(id));
    }

    @Operation(summary = "내 주변에 이 책을 소장하는 도서관 검색")
    @PostMapping("/{id}/prox")
    public UserProxLibraryDto getProxLibraryBook(@PathVariable("id") Long id, @RequestBody LibraryForm form){
        return userProxLibraryService.createUserProxLibraryByBookIdAndGeo(id, form.getLatitude(), form.getLongitude(), 10.0);
    }
}

package com.proxbook.finder.domain.book.api;

import com.proxbook.finder.domain.book.dto.BookLibraryPageDto;
import com.proxbook.finder.domain.book.dto.BookPageDto;
import com.proxbook.finder.domain.library.api.form.ProxLibraryForm;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.service.BookService;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryPageDto;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import com.proxbook.finder.domain.reports.book.service.BookReportsService;
import com.proxbook.finder.global.response.BaseListResponse;
import com.proxbook.finder.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public BaseResponse<BookPageDto> searchBookByName(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ){
        return new BaseResponse<>(bookService.readBookByTitle(title, page));
    }

    @Operation(summary = "책의 ISBN으로 검색")
    @GetMapping("/{id}")
    public BaseResponse<BookDto> getBookByBookId(
            @Parameter(description = "책 ID(ISBN)")
            @PathVariable("id") Long id){
        return new BaseResponse<>(bookService.readBookByBookId(id));
    }

    @Operation(summary = "이 책을 소장하는 도서관 검색")
    @GetMapping("/{id}/library")
    public BaseResponse<BookLibraryPageDto> getLibraryByBookId(
            @Parameter(description = "책 ID(ISBN)")
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ){
        BookLibraryPageDto bookLibraries = bookService.readLibraryByBookId(id, page);
        return new BaseResponse<>(bookLibraries);
    }

    @Operation(summary = "내 주변에 이 책을 소장하는 도서관 검색")
    @PostMapping("/{id}/prox")
    public BaseResponse<UserProxLibraryDto> getProxLibraryBook(
            @Parameter(description = "책 ID(ISBN)")
            @PathVariable("id") Long id, @RequestBody ProxLibraryForm form){
        return new BaseResponse<>(userProxLibraryService.createUserProxLibraryByBookIdAndGeo(id, form.getLatitude(), form.getLongitude(), form.getRange()));
    }

}

package com.proxbook.finder.domain.book.api;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.service.BookService;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.global.response.BaseListResponse;
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

    @GetMapping()
    public BaseListResponse<BookDto> searchBookByName(@RequestParam(value = "title", required = false) String title){
        return new BaseListResponse<>(bookService.readBookByTitle(title));
    }

    @GetMapping("/{id}")
    public BookDto getBookByBookId(@PathVariable("id") Long id, Model model){
        return bookService.readBookByBookId(id);
    }

    @GetMapping("/{id}/library")
    public BaseListResponse<LibraryDto> getLibraryByBookId(@PathVariable("id") Long id, Model model){
        return new BaseListResponse<>(libraryService.readLibraryByBookId(id));
    }
}

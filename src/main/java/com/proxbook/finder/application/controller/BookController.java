package com.proxbook.finder.application.controller;

import com.proxbook.finder.aop.annotation.MethodTimeChecker;
import com.proxbook.finder.application.form.LibraryBookForm;
import com.proxbook.finder.application.service.UserProxLibrarySearchService;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.application.service.BookSearchService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/book")
@Controller
@Slf4j
public class BookController {

    private final BookSearchService bookSearchService;
    private final UserProxLibrarySearchService userProxLibrarySearchService;

    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "title", required = false) String title, Model model){
        if(title!=null) {
            List<BookDto> books = bookSearchService.searchBooksByTitle(title);
            model.addAttribute("books", books);
        }
        return "bookSearch";
    }

    @GetMapping("/{bookId}")
    public String bookById(@PathVariable("bookId") String bookId, Model model){
        BookDto book = bookSearchService.searchBookById(bookId);
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("/prox-book")
    public String createProxBook(@RequestBody LibraryBookForm form, Model model){
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.saveUserProxLibraryByBook(
                form.getBookId(),
                form.getLatitude(),
                form.getLongitude(),
                10.0
        );
        model.addAttribute("userProxLibrary", userProxLibraryDto);
        return "fragments/prox-library";
    }
}

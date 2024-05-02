package com.proxbook.finder.controller;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/book")
@Controller
@Slf4j
public class BookController {

    private final BookSearchService bookSearchService;

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
}

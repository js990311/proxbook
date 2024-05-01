package com.proxbook.finder.controller;

import com.proxbook.finder.dto.BookDto;
import com.proxbook.finder.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            log.warn(String.valueOf(books.size()));
            model.addAttribute("books", books);
        }
        return "bookSearch";
    }
}

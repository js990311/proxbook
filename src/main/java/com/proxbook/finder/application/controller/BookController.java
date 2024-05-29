package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryBookForm;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.service.BookErrorRegistService;
import com.proxbook.finder.domain.book.service.BookService;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
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
    private final BookService bookService;
    private final UserProxLibraryService userProxLibraryService;
    private final LibraryService libraryService;
    private final BookErrorRegistService bookErrorRegistService;

    @GetMapping("/search")
    public String getBookSearch(@RequestParam(value = "title", required = false) String title, Model model){
        if(title!=null) {
            List<BookDto> books = bookService.readBookByTitle(title);
            model.addAttribute("books", books);
            model.addAttribute("title", title);
        }
        return "bookSearch";
    }

    @GetMapping("/{bookId}")
    public String getBookByBookId(@PathVariable("bookId") Long bookId, Model model){
        BookDto book = bookService.readBookByBookId(bookId);
        List<LibraryDto> libraries = libraryService.readLibraryByBookId(bookId);
        model.addAttribute("book", book);
        model.addAttribute("libraries", libraries);
        return "book";
    }

    @PostMapping("/{bookId}/report")
    public String postReportBook(@PathVariable("bookId") Long bookId, @RequestParam("reason") String reason){
        Long errorLogId = bookErrorRegistService.registBookError(bookId, reason);
        return "redirect:/book-error/" + errorLogId;
    }

    @PostMapping("/prox-book")
    public String postProxBook(@RequestBody LibraryBookForm form, Model model){
        UserProxLibraryDto userProxLibraryDto = userProxLibraryService.createUserProxLibraryByBookIdAndGeo(
                form.getBookId(),
                form.getLatitude(),
                form.getLongitude(),
                10.0 // range는 추후 추가
        );
        model.addAttribute("userProxLibrary", userProxLibraryDto);
        return "fragments/prox-library";
    }
}

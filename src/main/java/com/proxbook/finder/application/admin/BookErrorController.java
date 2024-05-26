package com.proxbook.finder.application.admin;

import com.proxbook.finder.domain.book.dto.BookErrorLogDto;
import com.proxbook.finder.domain.book.service.error.BookErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/book-error")
@Controller
public class BookErrorController {
    private final BookErrorService bookErrorService;

    @GetMapping("")
    public String getIndex(Model model){
        List<BookErrorLogDto> logs = bookErrorService.readAllBookErrors();
        model.addAttribute("logs", logs);
        return "admin/book-error-log/book-error-logs";
    }

    @GetMapping("/{log-id}")
    public String getLogId(@PathVariable("log-id") Long logId, Model model){
        BookErrorLogDto log = bookErrorService.readBookErrorByLogId(logId);
        model.addAttribute("log", log);
        return "admin/book-error-log/book-error";
    }
}

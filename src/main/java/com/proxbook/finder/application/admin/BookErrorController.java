package com.proxbook.finder.application.admin;

import com.proxbook.finder.application.form.BookErrorRegistForm;
import com.proxbook.finder.domain.book.dto.BookErrorLogDto;
import com.proxbook.finder.domain.book.service.error.BookErrorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @ResponseBody
    @DeleteMapping("/{log-id}")
    public String deleteLogId(@PathVariable("log-id") Long logId){
        log.debug("delete, log-id : ", logId);
        bookErrorService.deleteBookErrors(logId);
        return "success";
    }

    @PostMapping("/regist")
    public String registBookError(@ModelAttribute BookErrorRegistForm form){
        return "admin/book-error-log/regist-book-error";
    }
}

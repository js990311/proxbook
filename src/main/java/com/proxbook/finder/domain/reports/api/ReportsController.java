package com.proxbook.finder.domain.reports.api;

import com.proxbook.finder.domain.reports.api.form.ReportsForm;
import com.proxbook.finder.domain.reports.api.response.book.BookReportsResponse;
import com.proxbook.finder.domain.reports.api.response.library.LibraryReportsResponse;
import com.proxbook.finder.domain.reports.book.service.BookReportsService;
import com.proxbook.finder.domain.reports.library.service.LibraryReportsService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ReportsController {
    private final BookReportsService bookReportsService;
    private final LibraryReportsService libraryReportsService;

    @PostMapping("/book/{id}/reports")
    public BookReportsResponse postBookReports(
            @Parameter(description = "문의할 책 ID(ISBN)")
            @PathVariable("id") Long id,
            @RequestBody ReportsForm form
            ){
        return bookReportsService.createBookReports(id, form.getTitle(), form.getMessage());
    }

    @PostMapping("/library/{id}/reports")
    public LibraryReportsResponse postLibraryReports(
            @Parameter(description = "문의할 도서관 ID")
            @PathVariable("id") Long id,
            @RequestBody ReportsForm form
    ){
        return libraryReportsService.createLibraryReports(id, form.getTitle(), form.getMessage());
    }
}

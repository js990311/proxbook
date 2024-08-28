package com.proxbook.finder.domain.reports.api;

import com.proxbook.finder.domain.reports.api.form.ReportsForm;
import com.proxbook.finder.domain.reports.api.response.BookReportsResponse;
import com.proxbook.finder.domain.reports.book.service.BookReportsService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ReportsController {
    private final BookReportsService bookReportsService;

    @PostMapping("/book/{id}/reports")
    public BookReportsResponse postBookReports(
            @Parameter(description = "문의할 책 ID(ISBN)")
            @PathVariable("id") Long id,
            @RequestBody ReportsForm form
            ){
        return bookReportsService.createBookReports(id, form.getTitle(), form.getMessage());
    }

}

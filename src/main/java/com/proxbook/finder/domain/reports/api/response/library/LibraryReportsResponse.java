package com.proxbook.finder.domain.reports.api.response.library;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.reports.api.response.BaseReportsResponse;
import com.proxbook.finder.domain.reports.base.entity.ReportsType;
import lombok.Getter;

@Getter
public class LibraryReportsResponse extends BaseReportsResponse {
    private Long libraryId;
    private String libraryName;

    public LibraryReportsResponse(Long id, String title, String message, Library library){
        super(id,title,message,ReportsType.LIBRARY);
        this.libraryId = library.getId();
        this.libraryName = library.getName();
    }
}

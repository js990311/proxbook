package com.proxbook.finder.domain.reports.library.entity;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.reports.base.entity.BaseReports;
import com.proxbook.finder.domain.reports.base.entity.ReportsType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class LibraryReports extends BaseReports {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "library_id", updatable = false, insertable = false)
    private Long libraryId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;

    public LibraryReports(Library library,String title, String message) {
        super(ReportsType.LIBRARY, title, message);
        connectLibrary(library);
    }

    private void connectLibrary(Library library){
        this.library = library;
    }

}

package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookSearchRepository {
    public List<BookDto> findBookBtTitle(String title);
    public Page<BookDto> findBookBtTitle(String title, int pageNumber, int pageSize);
    public List<BookDto> findBookByLibraryId(Long libraryId);

    public Page<BookDto> findBookByLibraryId(Long libraryId, int pageNumber, int pageSize);
    public Page<BookDto> findBookByLibraryIdAndBookTitle(Long libraryId, String title, int pageNumber, int pageSize);
}

package com.proxbook.finder.domain.library.opensearch;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 도서관 및 도서관 소장 도서에 대한 쿼리를 처리해주는 통합 리포지터리 인터페이스
 */
public interface LibrarySearchRepository {
    public Page<LibraryDto> findLibraryByName(String name, Pageable pageable);
    public Page<LibraryDto> findLibraryByNameOrAddress(String query, Pageable pageable);
    public Page<LibraryDto> findLibrariesByBookId(Long bookId, Pageable pageable);
    public Page<LibraryDto> findLibraryByAddress(String address, Pageable pageable);
}

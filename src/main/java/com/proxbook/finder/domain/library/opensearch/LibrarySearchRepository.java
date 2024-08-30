package com.proxbook.finder.domain.library.opensearch;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 도서관 및 도서관 소장 도서에 대한 쿼리를 처리해주는 통합 리포지터리 인터페이스
 */
public interface LibrarySearchRepository {
    public List<LibraryDto> findLibraryByName(String name);
    public List<LibraryDto> findLibraryByNameOrAddress(String name, String address);
    public List<LibraryDto> findLibrariesByBookId(Long bookId);
    public List<LibraryDto> findLibrariesByIdList(List<Long> idList);
    public List<LibraryDto> findLibraryByAddress(String address);
}

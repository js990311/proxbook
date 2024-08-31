package com.proxbook.finder.domain.library.opensearch;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class LibrarySearchRepositoryImpl implements LibrarySearchRepository {
    private final LibraryRepository libraryRepository;
    private final LibraryDocumentRepository libraryDocumentRepository;

    @Override
    public Page<LibraryDto> findLibraryByName(String name, Pageable pageable) {
        return libraryDocumentRepository.findByName(name, pageable)
                .map(LibraryDto::from);
    }

    @Override
    public Page<LibraryDto> findLibraryByNameOrAddress(String query, Pageable pageable) {
        return libraryDocumentRepository.findByNameOrAddress(query, query, pageable)
                .map(LibraryDto::from);
    }

    @Override
    public Page<LibraryDto> findLibrariesByBookId(Long bookId, Pageable pageable) {
        return libraryRepository.findLibrariesPageByBookId(bookId, pageable)
                .map(LibraryDto::from);
    }

    @Override
    public Page<LibraryDto> findLibraryByAddress(String address, Pageable pageable) {
        return libraryDocumentRepository.findByAddress(address, pageable)
                .map(LibraryDto::from);
    }

}

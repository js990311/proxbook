package com.proxbook.finder.domain.library.repository;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.entity.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public List<LibraryDto> findLibraryByName(String name) {
        return libraryDocumentRepository.findByNameNoriOrNameNgram(name, name)
                .stream()
                .map(LibraryDto::from)
                .toList();
    }

    @Override
    public List<LibraryDto> findLibraryByNameOrAddress(String name, String address) {
        return libraryDocumentRepository.findByNameOrAddress(name, address)
                .stream()
                .map(LibraryDto::from)
                .toList();
    }

    @Override
    public List<LibraryDto> findLibrariesByBookId(Long bookId) {
        return libraryRepository.findLibrariesByBookId(bookId)
                .stream()
                .map(LibraryDto::from)
                .toList();
    }

    @Override
    public List<LibraryDto> findLibrariesByIdList(List<Long> idList) {
        return libraryRepository.findLibrariesByIdList(idList)
                .stream()
                .map(LibraryDto::from)
                .toList();
    }

    @Override
    public List<LibraryDto> findLibraryByAddress(String address) {
        return libraryDocumentRepository.findByAddress(address)
                .stream()
                .map(LibraryDto::from)
                .toList();
    }

}

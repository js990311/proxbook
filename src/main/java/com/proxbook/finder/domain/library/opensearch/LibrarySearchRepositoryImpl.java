package com.proxbook.finder.domain.library.opensearch;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
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
        return libraryDocumentRepository.findByName(name)
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

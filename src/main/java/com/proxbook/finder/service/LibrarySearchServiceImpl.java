package com.proxbook.finder.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LibrarySearchServiceImpl implements LibrarySearchService{
    private final LibraryService libraryService;

    @Override
    public List<LibraryDto> searchLibraryByGeoAndBookId(String bookId, double latitude, double longitude) {
        List<Library> libraries = libraryService.searchLibraryByBookId(bookId);
        // List<LibraryDto> libraryDtos = libraries.stream().map(LibraryDto::new).toList();
        List<LibraryDto> libraryDtos = libraryService.filterLibrariesByGeo(libraries, latitude, longitude, 10.0).stream().map(LibraryDto::new).toList();
        return libraryDtos;
    }
}

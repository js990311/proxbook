package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.dto.LibraryDto;

import java.util.List;

public interface LibrarySearchService {
    public List<LibraryDto> readLibraryByLibraryName(String libraryName);

    public List<LibraryDto> readLibraryByLibraryNameOrAddress(String query);
}
package com.proxbook.finder.service;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryGeoDto;

import java.util.List;

public interface LibrarySearchService {
    public List<LibraryDto> searchLibraryByGeoAndBookId(String bookId, double latitude, double longitude);

    public List<LibraryDto> searchLibraryByGeo(double latitude, double longitude);

    public List<LibraryGeoDto> searchLibraryGeoByLibraryCodes(List<String> libraryCodes);
}

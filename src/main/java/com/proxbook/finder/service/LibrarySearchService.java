package com.proxbook.finder.service;

import com.proxbook.finder.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LibrarySearchService {
    /**
     *
     */
    public List<LibraryDto> searchLibraryByGeoAndBookId(String bookId, double latitude, double longitude);
}

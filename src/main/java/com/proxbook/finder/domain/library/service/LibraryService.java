package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.entity.Library;

import java.util.List;

/**
 * ProxLibraryService가 더 많이 사용됨
 */
@Deprecated
public interface LibraryService {
    public List<LibraryDto> findByGeo(double latitude, double longtitude, double range);
    public List<LibraryDto> searchLibraryByBookId(String bookId);
    public List<LibraryDto> findByLibraryIds(List<String> libraryIds);
}

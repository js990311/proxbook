package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.entity.Library;

import java.util.List;

/**
 * ProxLibraryService가 더 많이 사용됨
 */
public interface LibraryService {
    @Deprecated
    public List<LibraryDto> readLibraryByGeo(double latitude, double longtitude, double range);
    @Deprecated
    public List<LibraryDto> readLibraryByLibraryIds(List<Long> libraryIds);

    public List<LibraryDto> readLibraryByBookId(Long bookId);
    public List<LibraryDto> readLibraryByLibraryName(String libraryName);

    /**
     * 해당 도서관의 소장 도서를 가져옴
     * @param libraryId
     * @return
     */
    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId);
    public LibraryBookDto readLibraryBooksByLibraryIdAndBookTitle(Long libraryId, String title);

}

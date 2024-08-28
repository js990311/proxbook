package com.proxbook.finder.domain.reports.library.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.exception.LibraryNotFoundException;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.reports.api.response.library.LibraryReportsResponse;
import com.proxbook.finder.domain.reports.library.entity.LibraryReports;
import com.proxbook.finder.domain.reports.library.repository.LibraryReportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LibraryReportsService {
    private final LibraryReportsRepository libraryReportsRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public LibraryReportsResponse createLibraryReports(Long libraryId, String title, String message){
        Library library = libraryRepository.findById(libraryId).orElseThrow(()->new LibraryNotFoundException(libraryId));
        LibraryReports libraryReports = new LibraryReports(library, title, message);
        Long id = libraryReportsRepository.save(libraryReports).getId();
        return convertToResponse(library, libraryReports, id);
    }

    private LibraryReportsResponse convertToResponse(Library library, LibraryReports libraryReports, Long id){
        return new LibraryReportsResponse(id, libraryReports.getTitle(), libraryReports.getMessage(), library);
    }

}

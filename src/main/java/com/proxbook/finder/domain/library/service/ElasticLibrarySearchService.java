package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.aop.annotation.MethodTimeChecker;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.repository.document.LibraryDocument;
import com.proxbook.finder.domain.library.repository.LibraryDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticLibrarySearchService implements LibrarySearchService {
    private final LibraryDocumentRepository libraryDocumentRepository;

    @MethodTimeChecker
    @Override
    public List<LibraryDto> readLibraryByLibraryName(String libraryName) {
        return convertLibraryDto(libraryDocumentRepository.findByName(libraryName));
    }

    private List<LibraryDto> convertLibraryDto(List<LibraryDocument> libraries){
        return libraries.stream().map(LibraryDto::from).toList();
    }
}

package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.service.utils.DistanceCalculator;
import com.proxbook.finder.domain.librarybook.repository.LibraryBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Slf4j
@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibraryBookRepository libraryBookRepository;
    private final DistanceCalculator distanceCalculator;

    @Override
    public List<LibraryDto> readLibraryByGeo(double latitude, double longitude, double distance_range) {
        List<Library> libraries = libraryRepository.findAll();
        return filterLibrariesByGeo(libraries, latitude, longitude, distance_range).stream().map(this::convertLibraryDto).toList();
    }

    @Override
    public List<LibraryDto> readLibraryByBookId(String bookId) {
        List<String> librariesId = libraryBookRepository.findLibraryIdByBookId(bookId);
        return libraryRepository.findLibrariesByIdList(librariesId).stream().map(this::convertLibraryDto).toList();
    }

    @Override
    public List<LibraryDto> readLibraryByLibraryIds(List<String> libraryIds) {
        return libraryRepository.findLibrariesByIdList(libraryIds).stream().map(this::convertLibraryDto).toList();
    }

    /**
     * 거리 계산기
     * @param libraries
     * @param latitude
     * @param longitude
     * @param distance
     * @return
     */
    private List<Library> filterLibrariesByGeo(List<Library> libraries, double latitude, double longitude, double distance) {
        List<Library> ret = new ArrayList<>();
        for(Library library : libraries){
            if(distanceCalculator.calculateDistance(latitude, longitude, library.getLatitude(), library.getLongitude()) <= distance){
                ret.add(library);
            }
        }
        return ret;
    }

    private LibraryDto convertLibraryDto(Library library){
        return new LibraryDto(library);
    }
}

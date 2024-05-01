package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.librarybook.repository.LibraryBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private static int EARTH_RADIUS = 6371; // km

    private final LibraryRepository libraryRepository;
    private final LibraryBookRepository libraryBookRepository;

    @Override
    public List<Library> findByGeo(double latitude, double longitude, double distance_range) {
        List<Library> libraries = libraryRepository.findAll();
        return filterLibrariesByGeo(libraries, latitude, longitude, distance_range);
    }

    @Override
    public List<Library> searchLibraryByBookId(String bookId) {
        log.debug(bookId);
        List<String> librariesId = libraryBookRepository.findLibraryIdByBookId(bookId);
        log.debug(String.valueOf(librariesId.size()));
        return libraryRepository.findLibrariesByIdList(librariesId);
    }

    @Override
    public List<Library> filterLibrariesByGeo(List<Library> libraries, double latitude, double longitude, double distance) {
        List<Library> ret = new ArrayList<>();
        for(Library library : libraries){
            if(calculateDistance(latitude, longitude, library.getLatitude(), library.getLongitude()) <= distance){
                ret.add(library);
            }
        }
        return ret;
    }

    private double haversine(double val){
        return Math.pow(Math.sin(val / 2), 2);
    }

    private double calculateDistance(double fromLa, double fromLo, double toLa, double toLo){
        double dLa = Math.toRadians((toLa - fromLa));
        double dLo = Math.toRadians((toLo - fromLo));

        fromLa = Math.toRadians(fromLa);
        toLa = Math.toRadians(toLa);

        double a = haversine(dLa) + Math.cos(fromLa) * Math.cos(toLa) * haversine(dLo);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return EARTH_RADIUS * c;
    }
}

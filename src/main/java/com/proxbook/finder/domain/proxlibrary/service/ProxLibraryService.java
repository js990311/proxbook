package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.service.utils.DistanceCalculator;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.ProxLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProxLibraryService{
    private final LibraryRepository libraryRepository;
    private final ProxLibraryRepository proxLibraryRepository;
    private final DistanceCalculator distanceCalculator;

    public List<ProxLibrary> createProxLibraryByGeo(double latitude, double longitude, double range) {
        List<Library> libraries = libraryRepository.findAll();

        List<ProxLibrary> proxLibraries = new ArrayList<>();
        for(Library library : libraries){
            double distance = distanceCalculator.calculateDistance(
                    latitude, longitude,
                    library.getLatitude(), library.getLongitude()
            );
            if(distance <= range){
                proxLibraries.add(
                        new ProxLibrary.Builder()
                                .setDistance(distance)
                                .setLibrary(library)
                                .build()
                );
            }
        }
        return proxLibraries;
    }

    public List<ProxLibrary> createProxLibraryByBookIdAndGeo(Long bookId, double latitude, double longitude, double range) {
        List<Library> libraries = libraryRepository.findLibrariesByBookId(bookId);
        List<ProxLibrary> proxLibraries = new ArrayList<>();
        for(Library library : libraries){
            double distance = distanceCalculator.calculateDistance(
                    latitude, longitude,
                    library.getLatitude(), library.getLongitude()
            );
            if(distance <= range){
                proxLibraries.add(
                        new ProxLibrary.Builder()
                                .setDistance(distance)
                                .setLibrary(library)
                                .build()
                );
            }
        }
        return proxLibraries;
    }

    public List<ProxLibrary> readProxLibraryByUserProxLibraryId(Long userProxLibraryId) {
        List<ProxLibrary> proxLibraries = proxLibraryRepository.findByUserProxLibraryIdOrderByDistance(userProxLibraryId);
        return proxLibraries;
    }
}

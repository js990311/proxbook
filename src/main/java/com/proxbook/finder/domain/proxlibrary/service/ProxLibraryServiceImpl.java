package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.library.service.utils.DistanceCalculator;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.ProxLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProxLibraryServiceImpl implements ProxLibraryService{
    private final LibraryRepository libraryRepository;
    private final DistanceCalculator distanceCalculator;

    @Override
    public List<ProxLibrary.Builder> saveProxLibraryByGeo(double latitude, double longitude, double range) {
        List<Library> libraries = libraryRepository.findAll();

        List<ProxLibrary.Builder> proxLibraries = new ArrayList<>();
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
                );
            }
        }
        return proxLibraries;
    }
}

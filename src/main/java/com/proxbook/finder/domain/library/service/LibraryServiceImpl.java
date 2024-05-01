package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private static int EARTH_RADIUS = 6371; // km

    private final LibraryRepository libraryRepository;

    @Override
    public List<Library> findByGeo(double latitude, double longtitude, double distance_range) {
        List<Library> libraries = libraryRepository.findAll();

        List<Library> ret = new ArrayList<>();
        for(Library library : libraries){
            double distance = calculateDistance(longtitude, latitude, library.getLongtitude(), library.getLatitude());
            if(distance <= distance_range){
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

package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.library.service.utils.DistanceCalculator;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.ProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserProxLibraryServiceImpl implements UserProxLibraryService{
    private final DistanceCalculator distanceCalculator;
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final ProxLibraryRepository proxLibraryRepository;
    private final LibraryService libraryService;

    @Override
    public UserProxLibrary saveUserProxLibraryByGeo(double latitude, double longitude, double distance) {
        UserProxLibrary userProxLibrary = new UserProxLibrary();
        userProxLibraryRepository.save(userProxLibrary);

        List<Library> libraries = libraryService.findByGeo(latitude, longitude, distance);
        List<ProxLibrary> proxLibraries = new ArrayList<>();
        for(Library library : libraries){
            double libraryDistanceWithUser = distanceCalculator.calculateDistance(
                    latitude, longitude,
                    library.getLatitude(), library.getLongitude()
            );
            proxLibraries.add(
                    new ProxLibrary(userProxLibrary.getId(), library, libraryDistanceWithUser)
            );
        }
        userProxLibrary.addProxLibraries(proxLibraries);
        return userProxLibrary;
    }

    @Override
    public UserProxLibrary findUserProxLibraryById(Long id) {
        return null;
    }
}

package com.proxbook.finder.application.service;

import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserProxLibrarySearchServiceImpl implements UserProxLibrarySearchService{
    private final UserProxLibraryService userProxLibraryService;

    @Override
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double distance) {
        UserProxLibrary userProxLibrary = userProxLibraryService.saveUserProxLibraryByGeo(latitude, longitude, distance);
        UserProxLibraryDto userProxLibraryDto = new UserProxLibraryDto(userProxLibrary);
        return userProxLibraryDto;
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryById(Long id) {
        UserProxLibrary userProxLibrary = userProxLibraryService.findUserProxLibraryById(id);
        UserProxLibraryDto userProxLibraryDto = new UserProxLibraryDto(userProxLibrary);
        return userProxLibraryDto;
    }
}

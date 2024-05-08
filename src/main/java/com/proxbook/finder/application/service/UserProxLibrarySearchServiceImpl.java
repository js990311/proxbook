package com.proxbook.finder.application.service;

import com.proxbook.finder.application.service.utils.Base62Encoder;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserProxLibrarySearchServiceImpl implements UserProxLibrarySearchService {
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final UserProxLibraryService userProxLibraryService;

    @Override
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double distance) {
        UserProxLibrary userProxLibrary = userProxLibraryService.saveUserProxLibraryByGeo(latitude, longitude, distance);
        UserProxLibraryDto userProxLibraryDto = new UserProxLibraryDto(userProxLibrary);
        return userProxLibraryDto;
    }

    @Override
    public UserProxLibraryDto saveUserProxLibraryByBook(String bookId, double latitude, double longitude, double range) {
        UserProxLibrary userProxLibrary = userProxLibraryService.saveUserProxLibraryByBookIdAndGeo(bookId, latitude, longitude, range);
        UserProxLibraryDto userProxLibraryDto = new UserProxLibraryDto(userProxLibrary);
        return userProxLibraryDto;
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryById(Long id) {
        UserProxLibrary userProxLibrary = userProxLibraryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        UserProxLibraryDto userProxLibraryDto = new UserProxLibraryDto(userProxLibrary);
        return userProxLibraryDto;
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url) {
        long id = Base62Encoder.decode(url);
        UserProxLibrary userProxLibrary = userProxLibraryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        UserProxLibraryDto userProxLibraryDto = new UserProxLibraryDto(userProxLibrary);
        return userProxLibraryDto;
    }
}

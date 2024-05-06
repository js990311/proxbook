package com.proxbook.finder.application.service;

import com.proxbook.finder.application.service.utils.Base62Encoder;
import com.proxbook.finder.domain.proxlibrary.dto.ProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.ProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserProxLibrarySearchServiceImpl implements UserProxLibrarySearchService{
    private final UserProxLibraryService userProxLibraryService;
    private final ProxLibraryRepository proxLibraryRepository;
    private final UserProxLibraryRepository userProxLibraryRepository;

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

    @Override
    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url) {
        long id = Base62Encoder.decode(url);
        List<ProxLibraryDto> proxLibraryDtos = null;
        if(userProxLibraryRepository.existsById(id)){
            List<ProxLibrary> proxLibraries = proxLibraryRepository.findByUserProxLibraryIdOrderByDistance(id);
            proxLibraryDtos = proxLibraries.stream().map(ProxLibraryDto::new).toList();
        }
        UserProxLibraryDto ret = new UserProxLibraryDto(url, proxLibraryDtos);
        return ret;
    }
}

package com.proxbook.finder.application.service;

import com.proxbook.finder.application.service.utils.Base62Encoder;
import com.proxbook.finder.domain.proxlibrary.dto.ProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxBookLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProx;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxBookLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.ProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxRepository;
import com.proxbook.finder.domain.proxlibrary.service.UserProxBookLibraryService;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserProxSearchServiceImpl implements UserProxSearchService {
    private final ProxLibraryRepository proxLibraryRepository;
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final UserProxLibraryService userProxLibraryService;
    private final UserProxBookLibraryService userProxBookLibraryService;
    private final UserProxRepository userProxRepository;


    @Override
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double distance) {
        UserProxLibrary userProxLibrary = userProxLibraryService.saveUserProxLibraryByGeo(latitude, longitude, distance);
        UserProxLibraryDto userProxLibraryDto = new UserProxLibraryDto(userProxLibrary);
        return userProxLibraryDto;
    }

    @Override
    public UserProxBookLibraryDto saveUserProxBookLibrary(String bookId, double latitude, double longitude, double range) {
        UserProxBookLibrary userProxBookLibrary = userProxBookLibraryService.saveUserProxBookLibraryByBookIdAndGeo(bookId, latitude, longitude, range);
        UserProxBookLibraryDto userProxBookLibraryDto = new UserProxBookLibraryDto(userProxBookLibrary);
        return userProxBookLibraryDto;
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

        UserProx userProx =  userProxRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        UserProxLibraryDto userProxLibraryDto = null;
        if(userProx.getDtype().equals("library")){
            userProxLibraryDto = new UserProxLibraryDto((UserProxLibrary) userProx);
        }else if(userProx.getDtype().equals("book")){
            userProxLibraryDto = new UserProxBookLibraryDto((UserProxBookLibrary) userProx);
        }
        return userProxLibraryDto;
    }
}

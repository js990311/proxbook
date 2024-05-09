package com.proxbook.finder.application.service;

import com.proxbook.finder.application.service.utils.Base62ShortenUrlService;
import com.proxbook.finder.application.service.utils.ShortenUrlService;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.proxlibrary.dto.ProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.service.ProxLibraryService;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserProxLibrarySearchServiceImpl implements UserProxLibrarySearchService {
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final ShortenUrlService shortenUrlService;
    private final UserProxLibraryService userProxLibraryService;
    private final ProxLibraryService proxLibraryService;

    @Override
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double range) {
        UserProxLibrary userProxLibrary = userProxLibraryService.saveUserProxLibraryByGeo(latitude, longitude, range);
        return buildUserProxLibraryDto(userProxLibrary);
    }

    @Override
    public UserProxLibraryDto saveUserProxLibraryByBook(String bookId, double latitude, double longitude, double range) {
        UserProxLibrary userProxLibrary = userProxLibraryService.saveUserProxLibraryByBookIdAndGeo(bookId, latitude, longitude, range);
        return buildUserProxLibraryDto(userProxLibrary);
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryById(Long id) {
        List<ProxLibrary> proxLibraries = proxLibraryService.findProxLibraryByUserProxLibraryId(id);
        UserProxLibrary userProxLibrary = userProxLibraryRepository.findFetchJoinBookById(id);

        return buildUserProxLibraryDto(userProxLibrary,proxLibraries);
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url) {
        Long id = shortenUrlService.decodeUrl(url);
        return findUserProxLibraryById(id);
    }

    private UserProxLibraryDto buildUserProxLibraryDto(UserProxLibrary userProxLibrary, List<ProxLibrary> proxLibraries){
        return new UserProxLibraryDto.Builder()
                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
                .setLibraries(proxLibraries.stream().map(ProxLibraryDto::new).toList())
                .setBook(userProxLibrary.getBook())
                .setRange(userProxLibrary.getRange())
                .setLatitude(userProxLibrary.getLatitude())
                .setLongitude(userProxLibrary.getLongitude())
                .build()
        ;
    }

    private UserProxLibraryDto buildUserProxLibraryDto(UserProxLibrary userProxLibrary){
        return new UserProxLibraryDto.Builder()
                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
                .setLibraries(userProxLibrary.getProxLibraries().stream().map(ProxLibraryDto::new).toList())
                .setBook(userProxLibrary.getBook())
                .setRange(userProxLibrary.getRange())
                .setLatitude(userProxLibrary.getLatitude())
                .setLongitude(userProxLibrary.getLongitude())
                .build()
        ;
    }

}

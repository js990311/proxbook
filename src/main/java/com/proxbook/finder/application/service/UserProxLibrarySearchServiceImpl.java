package com.proxbook.finder.application.service;

import com.proxbook.finder.application.service.utils.Base62ShortenUrlService;
import com.proxbook.finder.application.service.utils.ShortenUrlService;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.proxlibrary.dto.ProxLibraryDto;
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
    private final ShortenUrlService shortenUrlService;
    private final UserProxLibraryService userProxLibraryService;

    @Override
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double range) {
        return userProxLibraryService.saveUserProxLibraryByGeo(latitude,longitude,range);
    }

    @Override
    public UserProxLibraryDto saveUserProxLibraryByBook(String bookId, double latitude, double longitude, double range) {
        return userProxLibraryService.saveUserProxLibraryByBookIdAndGeo(bookId, latitude, longitude, range);
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryById(Long id) {
        UserProxLibrary userProxLibrary = userProxLibraryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        UserProxLibraryDto.Builder builder = new UserProxLibraryDto.Builder();
        UserProxLibraryDto userProxLibraryDto = builder
                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
                .setLibraries(userProxLibrary.getProxLibraries().stream().map(ProxLibraryDto::new).toList())
                .setBookDto(userProxLibrary.getBook())
                .build();
        return userProxLibraryDto;
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url) {
//        long id = shortenUrlService.decodeUrl(url);
//        UserProxLibrary userProxLibrary = userProxLibraryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        UserProxLibraryDto.Builder builder = new UserProxLibraryDto.Builder();
//        UserProxLibraryDto userProxLibraryDto = builder
//                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
//                .setLibraries(userProxLibrary.getProxLibraries().stream().map(ProxLibraryDto::new).toList())
//                .setBookDto(userProxLibrary.getBook())
//                .build();
//        return userProxLibraryDto;
        return userProxLibraryService.findUserProxLibraryByShortenUrl(url);
    }


}

package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.aop.annotation.MethodTimeChecker;
import com.proxbook.finder.application.service.utils.ShortenUrlService;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.service.BookService;
import com.proxbook.finder.domain.proxlibrary.dto.ProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.ProxLibraryRepository;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class UserProxLibraryImpl implements UserProxLibraryService{
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final BookService bookService;
    private final ProxLibraryService proxLibraryService;
    private final ShortenUrlService shortenUrlService;
    private final ProxLibraryRepository proxLibraryRepository;

    @Override
    public UserProxLibraryDto saveUserProxLibraryByGeo(double latitude, double longitude, double range) {
        UserProxLibrary userProxLibrary = new UserProxLibrary.Builder()
                .setRange(range)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .build();
        userProxLibraryRepository.save(userProxLibrary);

        List<ProxLibrary.Builder> proxLibraryBuilders = proxLibraryService.saveProxLibraryByGeo(latitude, longitude, range);
        List<ProxLibrary> proxLibraries = proxLibraryBuilders.stream().map((proxLibraryBuilder) -> {
            proxLibraryBuilder.setUserProxLibrary(userProxLibrary);
            return proxLibraryBuilder.build();
        }).toList();
        userProxLibrary.addProxLibraries(proxLibraries);

        List<ProxLibraryDto> proxLibraryDtos = proxLibraries.stream().map(ProxLibraryDto::new).toList();

        return new UserProxLibraryDto.Builder()
                .setLibraries(proxLibraryDtos)
                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
                .setRange(userProxLibrary.getRange())
                .setLatitude(userProxLibrary.getLatitude())
                .setLongitude(userProxLibrary.getLongitude())
                .build();
    }

    @Override
    public UserProxLibraryDto saveUserProxLibraryByBookIdAndGeo(String bookId, double latitude, double longitude, double range) {
        Book book = bookService.findBookById(bookId);
        UserProxLibrary userProxLibrary = new UserProxLibrary.Builder()
                .setBook(book)
                .setRange(range)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .build();
        userProxLibraryRepository.save(userProxLibrary);
        List<ProxLibrary.Builder> proxLibraryBuilders = proxLibraryService.saveProxLibraryByGeo(latitude, longitude, range);
        List<ProxLibrary> proxLibraries = proxLibraryBuilders.stream().map((proxLibraryBuilder) -> {
            proxLibraryBuilder.setUserProxLibrary(userProxLibrary);
            return proxLibraryBuilder.build();
        }).toList();
        userProxLibrary.addProxLibraries(proxLibraries);

        List<ProxLibraryDto> proxLibraryDtos = proxLibraries.stream().map(ProxLibraryDto::new).toList();

        return new UserProxLibraryDto.Builder()
                .setLibraries(proxLibraryDtos)
                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
                .setBook(book)
                .setRange(userProxLibrary.getRange())
                .setLatitude(userProxLibrary.getLatitude())
                .setLongitude(userProxLibrary.getLongitude())
                .build();
    }

    @Override
    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url) {
        UserProxLibraryDto.Builder builder = new UserProxLibraryDto.Builder();

        Long id = shortenUrlService.decodeUrl(url);

        List<ProxLibrary> proxLibraries = proxLibraryService.findProxLibraryByUserProxLibraryId(id);
        UserProxLibrary userProxLibrary = userProxLibraryRepository.findFetchJoinBookById(id);

        log.info(String.valueOf(id));

        builder
                .setUrl(url)
                .setLibraries(proxLibraries.stream().map(ProxLibraryDto::new).toList())
                .setBook(userProxLibrary.getBook())
                .setRange(userProxLibrary.getRange())
                .setLatitude(userProxLibrary.getLatitude())
                .setLongitude(userProxLibrary.getLongitude())
        ;

        return builder.build();
    }

}

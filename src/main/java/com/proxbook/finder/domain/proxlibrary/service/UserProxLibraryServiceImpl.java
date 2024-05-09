package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.application.service.UserProxLibrarySearchService;
import com.proxbook.finder.application.service.utils.ShortenUrlService;
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
public class UserProxLibraryServiceImpl implements UserProxLibraryService {
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final BookService bookService;
    private final ProxLibraryService proxLibraryService;
    private final ShortenUrlService shortenUrlService;
    private final ProxLibraryRepository proxLibraryRepository;

    @Override
    public UserProxLibrary saveUserProxLibraryByGeo(double latitude, double longitude, double range) {
        List<ProxLibrary> proxLibraries = proxLibraryService.saveProxLibraryByGeo(latitude, longitude, range);

        UserProxLibrary userProxLibrary = new UserProxLibrary.Builder()
                .setRange(range)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setProxLibraries(proxLibraries)
                .build();

        userProxLibraryRepository.save(userProxLibrary);
        return userProxLibrary;
    }

    @Override
    public UserProxLibrary saveUserProxLibraryByBookIdAndGeo(String bookId, double latitude, double longitude, double range) {
        List<ProxLibrary> proxLibraries = proxLibraryService.saveProxLibraryByBookIdAndGeo(bookId,latitude, longitude, range);
        Book book = bookService.findBookById(bookId);

        UserProxLibrary userProxLibrary = new UserProxLibrary.Builder()
                .setBook(book)
                .setRange(range)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setProxLibraries(proxLibraries)
                .build();

        userProxLibraryRepository.save(userProxLibrary);
        return userProxLibrary;
    }
}

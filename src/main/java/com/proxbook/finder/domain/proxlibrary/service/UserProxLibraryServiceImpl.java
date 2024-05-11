package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.proxlibrary.dto.ProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.utils.ShortenUrlService;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.book.service.BookUpdateService;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final BookRepository bookRepository;
    private final BookUpdateService bookUpdateService;
    private final ProxLibraryService proxLibraryService;
    private final ShortenUrlService shortenUrlService;

    @Override
    public UserProxLibraryDto createUserProxLibraryByGeo(double latitude, double longitude, double range) {
        List<ProxLibrary> proxLibraries = proxLibraryService.createProxLibraryByGeo(latitude, longitude, range);

        UserProxLibrary userProxLibrary = UserProxLibrary.builder()
                .setRange(range)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setProxLibraries(proxLibraries)
                .build();

        userProxLibrary = userProxLibraryRepository.save(userProxLibrary);
        return convertUserProxLibraryDto(userProxLibrary);
    }

    @Override
    public UserProxLibraryDto createUserProxLibraryByBookIdAndGeo(String bookId, double latitude, double longitude, double range) {
        List<ProxLibrary> proxLibraries = proxLibraryService.createProxLibraryByBookIdAndGeo(bookId,latitude, longitude, range);
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);

        if(bookUpdateService.needUpdate(book)) {
            book = bookUpdateService.updateBook(book);
        }

        UserProxLibrary userProxLibrary = UserProxLibrary.builder()
                .setBook(book)
                .setRange(range)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setProxLibraries(proxLibraries)
                .build();

        userProxLibraryRepository.save(userProxLibrary);
        return  convertUserProxLibraryDto(userProxLibrary);
    }

    @Override
    public UserProxLibraryDto readUserProxLibraryByShortenUrl(String url) {
        Long id = shortenUrlService.decodeUrl(url);
        return findUserProxLibraryById(id);
    }

    private UserProxLibraryDto findUserProxLibraryById(Long id) {
        List<ProxLibrary> proxLibraries = proxLibraryService.readProxLibraryByUserProxLibraryId(id);
        UserProxLibrary userProxLibrary = userProxLibraryRepository.findFetchJoinBookById(id);
        return convertUserProxLibraryDto(userProxLibrary,proxLibraries);
    }

    private UserProxLibraryDto convertUserProxLibraryDto(UserProxLibrary userProxLibrary, List<ProxLibrary> proxLibraries){
        return UserProxLibraryDto.builder()
                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
                .setLibraries(proxLibraries.stream().map(ProxLibraryDto::new).toList())
                .setBook(userProxLibrary.getBook())
                .setRange(userProxLibrary.getRange())
                .setLatitude(userProxLibrary.getLatitude())
                .setLongitude(userProxLibrary.getLongitude())
                .build();
    }

    private UserProxLibraryDto convertUserProxLibraryDto(UserProxLibrary userProxLibrary){
        return UserProxLibraryDto.builder()
                .setUrl(shortenUrlService.encodeUrl(userProxLibrary.getId()))
                .setLibraries(userProxLibrary.getProxLibraries().stream().map(ProxLibraryDto::new).toList())
                .setBook(userProxLibrary.getBook())
                .setRange(userProxLibrary.getRange())
                .setLatitude(userProxLibrary.getLatitude())
                .setLongitude(userProxLibrary.getLongitude())
                .build();
    }
}

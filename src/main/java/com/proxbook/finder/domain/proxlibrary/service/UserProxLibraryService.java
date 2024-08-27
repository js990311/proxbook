package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.book.exception.BookNotFoundException;
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
public class UserProxLibraryService {
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final BookRepository bookRepository;
    private final BookUpdateService bookUpdateService;
    private final ProxLibraryService proxLibraryService;
    private final ShortenUrlService shortenUrlService;

    /**
     *
     * @param latitude  위도
     * @param longitude 경도
     * @param range 범위(미터단위로 입력됨)
     * @return
     */
    public UserProxLibraryDto createUserProxLibraryByGeo(double latitude, double longitude, double range) {
        double kmRange = range / 1000;
        List<ProxLibrary> proxLibraries = proxLibraryService.createProxLibraryByGeo(latitude, longitude, kmRange);

        UserProxLibrary userProxLibrary = UserProxLibrary.builder()
                .setRange(range)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setProxLibraries(proxLibraries)
                .build();

        userProxLibrary = userProxLibraryRepository.save(userProxLibrary);
        return convertUserProxLibraryDto(userProxLibrary);
    }

    public UserProxLibraryDto createUserProxLibraryByBookIdAndGeo(Long bookId, double latitude, double longitude, double range) {
        double kmRange = range / 1000;
        List<ProxLibrary> proxLibraries = proxLibraryService.createProxLibraryByBookIdAndGeo(bookId,latitude, longitude, kmRange);
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));

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

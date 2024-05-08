package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.service.BookService;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import com.proxbook.finder.domain.proxlibrary.repository.UserProxLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserProxLibraryImpl implements UserProxLibraryService{
    private final UserProxLibraryRepository userProxLibraryRepository;
    private final BookService bookService;
    private final ProxLibraryService proxLibraryService;

    @Override
    public UserProxLibrary saveUserProxLibraryByGeo(double latitude, double longitude, double range) {
        UserProxLibrary userProxLibrary = new UserProxLibrary();
        userProxLibraryRepository.save(userProxLibrary);

        List<ProxLibrary> proxLibraries = proxLibraryService.saveProxLibraryByGeo(latitude, longitude, range);
        userProxLibrary.addProxLibraries(proxLibraries);

        return userProxLibrary;
    }

    @Override
    public UserProxLibrary saveUserProxLibraryByBookIdAndGeo(String bookId, double latitude, double longitude, double range) {
        Book book = bookService.findBookById(bookId);
        UserProxLibrary userProxBookLibrary = new UserProxLibrary(book);
        userProxLibraryRepository.save(userProxBookLibrary);

        List<ProxLibrary> proxLibraries = proxLibraryService.saveProxLibraryByGeo(latitude, longitude, range);
        userProxBookLibrary.addProxLibraries(proxLibraries);

        return userProxBookLibrary;
    }

}

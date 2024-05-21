package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.aop.annotation.MethodTimeChecker;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.service.utils.DistanceCalculator;
import com.proxbook.finder.domain.librarybook.repository.LibraryBookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibraryBookRepository libraryBookRepository;
    private final BookRepository bookRepository;
    private final DistanceCalculator distanceCalculator;

    @Override
    public List<LibraryDto> readLibraryByGeo(double latitude, double longitude, double distance_range) {
        List<Library> libraries = libraryRepository.findAll();
        return filterLibrariesByGeo(libraries, latitude, longitude, distance_range).stream().map(this::convertLibraryDto).toList();
    }

    @Override
    public List<LibraryDto> readLibraryByBookId(Long bookId) {
        List<Long> librariesId = libraryBookRepository.findLibraryIdByBookId(bookId);
        return libraryRepository.findLibrariesByIdList(librariesId).stream().map(this::convertLibraryDto).toList();
    }

    @MethodTimeChecker
    @Override
    public List<LibraryDto> readLibraryByLibraryName(String libraryName) {
        return libraryRepository.findLibrariesByName(libraryName).stream().map(this::convertLibraryDto).toList();
    }

    @Override
    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(EntityNotFoundException::new);
        List<Book> libraryBooks = bookRepository.findLibraryBooksByLibraryId(libraryId);
        return LibraryBookDto.builder()
                .setLibrary(convertLibraryDto(library))
                .setBooks(libraryBooks.stream().map(BookDto::from).toList())
                .build();
    }

    @Override
    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId, int page) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(EntityNotFoundException::new);
        PageRequest pageRequest = PageRequest.of(
                page, // 몇번째 페이지 인지
                20 // 페이지 사이즈
        );
        Page<Book> libraryBooks = bookRepository.findLibraryBooksByLibraryId(libraryId, pageRequest);
        return convertLibraryBookDto(library, libraryBooks);
    }

    @Override
    public LibraryBookDto readLibraryBooksByLibraryIdAndBookTitle(Long libraryId, String title, int page) {
        PageRequest pageRequest = PageRequest.of(
                page, // 몇번째 페이지 인지
                20 // 페이지 사이즈
        );
        Library library = libraryRepository.findById(libraryId).orElseThrow(EntityNotFoundException::new);
        Page<Book> books = bookRepository.findLibraryBooksByLibraryIdAndBookTitle(libraryId, title, pageRequest);
        return convertLibraryBookDto(library, books);
    }

    @Override
    public List<LibraryDto> readLibraryByLibraryIds(List<Long> libraryIds) {
        return libraryRepository.findLibrariesByIdList(libraryIds).stream().map(this::convertLibraryDto).toList();
    }

    /**
     * 거리 계산기
     * @param libraries
     * @param latitude
     * @param longitude
     * @param distance
     * @return
     */
    private List<Library> filterLibrariesByGeo(List<Library> libraries, double latitude, double longitude, double distance) {
        List<Library> ret = new ArrayList<>();
        for(Library library : libraries){
            if(distanceCalculator.calculateDistance(latitude, longitude, library.getLatitude(), library.getLongitude()) <= distance){
                ret.add(library);
            }
        }
        return ret;
    }

    private LibraryDto convertLibraryDto(Library library){
        return LibraryDto.from(library);
    }

    private LibraryBookDto convertLibraryBookDto(Library library, Page<Book> books){
        return LibraryBookDto.builder()
                .setLibrary(convertLibraryDto(library))
                .setBooks(books.stream().map(BookDto::from).toList())
                .setNowPage(books.getNumber())
                .setTotalPage(books.getTotalPages())
                .build();

    }
}

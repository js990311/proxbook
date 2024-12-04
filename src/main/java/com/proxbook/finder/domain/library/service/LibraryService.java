package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryBookPageDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryPageDto;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.exception.LibraryNotFoundException;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    public LibraryDto findByLibraryId(Long id){
        return convertLibraryDto(libraryRepository.findById(id).orElseThrow(()->new LibraryNotFoundException(id)));
    }

    public LibraryPageDto readLibraryByLibraryName(String libraryName, Integer page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20
        );
        Page<LibraryDto> librariese = libraryRepository.findLibrariesByName(libraryName, pageRequest).map(LibraryDto::from);
        return new LibraryPageDto(librariese);
    }

    public LibraryPageDto readLibraryByAddress(String address, Integer page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20
        );

        Page<LibraryDto> librariese = libraryRepository.findLibrariesByAddress(address, pageRequest).map(LibraryDto::from);
        return new LibraryPageDto(librariese);
    }

    public LibraryPageDto readLibraryByLibraryNameOrAddress(String query, Integer page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20
        );
        Page<LibraryDto> librariese = libraryRepository.findLibraryByNameOrAddress(query, pageRequest).map(LibraryDto::from);
        return new LibraryPageDto(librariese);
    }

    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(()->new LibraryNotFoundException(libraryId));
        List<BookDto> books = bookRepository.findLibraryBooksByLibraryId(libraryId).stream().map(BookDto::from).toList();
        return LibraryBookDto.builder()
                .setLibrary(convertLibraryDto(library))
                .setBooks(books)
                .build();
    }

    public LibraryBookPageDto readLibraryBooksByLibraryId(Long libraryId, int page) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(()->new LibraryNotFoundException(libraryId));
        Page<BookDto> books = bookRepository.findLibraryBooksByLibraryId(libraryId, PageRequest.of(page, 20)).map(BookDto::from);
        return new LibraryBookPageDto(convertLibraryDto(library), books);
    }

    /* dto 변환 함수 */

    private LibraryDto convertLibraryDto(Library library){
        return LibraryDto.from(library);
    }

    private LibraryBookDto convertLibraryBookDto(Library library, Page<BookDto> books){
        return LibraryBookDto.builder()
                .setLibrary(convertLibraryDto(library))
                .setBooks(books.toList())
                .setNowPage(books.getNumber())
                .setTotalPage(books.getTotalPages())
                .build();

    }
}

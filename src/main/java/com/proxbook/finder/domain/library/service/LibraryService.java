package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.opensearch.repository.BookSearchRepository;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryBookPageDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryPageDto;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.exception.LibraryNotFoundException;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.opensearch.LibrarySearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibrarySearchRepository librarySearchRepository;
    private final BookSearchRepository bookSearchRepository;

    public LibraryDto findByLibraryId(Long id){
        return convertLibraryDto(libraryRepository.findById(id).orElseThrow(()->new LibraryNotFoundException(id)));
    }

    public LibraryPageDto readLibraryByBookId(Long bookId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 20);
        Page<LibraryDto> libraries = librarySearchRepository.findLibrariesByBookId(bookId, pageRequest);
        return new LibraryPageDto(libraries);
    }

    public LibraryPageDto readLibraryByLibraryName(String libraryName, Integer page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20
        );
        Page<LibraryDto> librariese = librarySearchRepository.findLibraryByName(libraryName, pageRequest);
        return new LibraryPageDto(librariese);
    }

    public LibraryPageDto readLibraryByAddress(String address, Integer page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20
        );
        Page<LibraryDto> librariese = librarySearchRepository.findLibraryByAddress(address, pageRequest);
        return new LibraryPageDto(librariese);
    }

    public LibraryPageDto readLibraryByLibraryNameOrAddress(String query, Integer page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20
        );
        Page<LibraryDto> librariese = librarySearchRepository.findLibraryByNameOrAddress(query, pageRequest);
        return new LibraryPageDto(librariese);
    }

    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(()->new LibraryNotFoundException(libraryId));
        List<BookDto> books = bookSearchRepository.findBookByLibraryId(libraryId);
        return LibraryBookDto.builder()
                .setLibrary(convertLibraryDto(library))
                .setBooks(books)
                .build();
    }

    public LibraryBookPageDto readLibraryBooksByLibraryId(Long libraryId, int page) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(()->new LibraryNotFoundException(libraryId));
        Page<BookDto> books = bookSearchRepository.findBookByLibraryId(libraryId, page, 20);
        return new LibraryBookPageDto(convertLibraryDto(library), books);
    }

    public LibraryBookDto readLibraryBooksByLibraryIdAndBookTitle(Long libraryId, String title, int page) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(()->new LibraryNotFoundException(libraryId));
        Page<BookDto> books = bookSearchRepository.findBookByLibraryIdAndBookTitle(libraryId, title, page, 20);
        return convertLibraryBookDto(library, books);
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

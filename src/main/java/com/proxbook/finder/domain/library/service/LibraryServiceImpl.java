package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.aop.annotation.MethodTimeChecker;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.book.repository.BookSearchRepository;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.repository.LibraryRepository;
import com.proxbook.finder.domain.library.repository.LibrarySearchRepository;
import com.proxbook.finder.domain.library.service.utils.DistanceCalculator;
import com.proxbook.finder.domain.librarybook.repository.LibraryBookRepository;
import com.proxbook.finder.domain.librarybook.repository.document.LibraryBookDocument;
import com.proxbook.finder.domain.librarybook.repository.LibraryBookDocumentRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibrarySearchRepository librarySearchRepository;
    private final BookSearchRepository bookSearchRepository;

    @Override
    public List<LibraryDto> readLibraryByBookId(Long bookId) {
        return librarySearchRepository.findLibrariesByBookId(bookId);
    }

    @Override
    public List<LibraryDto> readLibraryByLibraryName(String libraryName) {
        return librarySearchRepository.findLibraryByName(libraryName);
    }

    @Override
    public List<LibraryDto> readLibraryByAddress(String address) {
        return librarySearchRepository.findLibraryByAddress(address);
    }

    @Override
    public List<LibraryDto> readLibraryByLibraryNameOrAddress(String query) {
        return librarySearchRepository.findLibraryByNameOrAddress(query,query);
    }

    @Override
    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(EntityNotFoundException::new);
        List<BookDto> books = bookSearchRepository.findBookByLibraryId(libraryId);
        return LibraryBookDto.builder()
                .setLibrary(convertLibraryDto(library))
                .setBooks(books)
                .build();
    }

    @Override
    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId, int page) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(EntityNotFoundException::new);
        Page<BookDto> books = bookSearchRepository.findBookByLibraryId(libraryId, page, 20);
        return convertLibraryBookDto(library, books);
    }

    @Override
    public LibraryBookDto readLibraryBooksByLibraryIdAndBookTitle(Long libraryId, String title, int page) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(EntityNotFoundException::new);
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

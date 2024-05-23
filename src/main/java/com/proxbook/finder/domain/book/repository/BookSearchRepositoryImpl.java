package com.proxbook.finder.domain.book.repository;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.librarybook.repository.LibraryBookDocumentRepository;
import com.proxbook.finder.domain.librarybook.repository.LibraryBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class BookSearchRepositoryImpl implements BookSearchRepository{
    private final BookRepository bookRepository;
    private final BookDocumentRepository bookDocumentRepository;
    private final LibraryBookRepository libraryBookRepository;
    private final LibraryBookDocumentRepository libraryBookDocumentRepository;

    @Override
    public List<BookDto> findBookBtTitle(String title) {
        return bookDocumentRepository.findByTitleNoriOrTitleNgram(title, title)
                .stream()
                .map(BookDto::from)
                .toList();
    }

    @Override
    public Page<BookDto> findBookBtTitle(String title, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize
        );
        return bookDocumentRepository.findByTitleNoriOrTitleNgram(title, title, pageRequest)
                .map(BookDto::from);
    }

    @Override
    public List<BookDto> findBookByLibraryId(Long libraryId) {
        return bookRepository.findLibraryBooksByLibraryId(libraryId)
                .stream()
                .map(BookDto::from)
                .toList();
    }

    @Override
    public Page<BookDto> findBookByLibraryId(Long libraryId, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize
        );
        return bookRepository.findLibraryBooksByLibraryId(libraryId, pageRequest)
                .map(BookDto::from);
    }

    @Override
    public Page<BookDto> findBookByLibraryIdAndBookTitle(Long libraryId, String title, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize
        );
        return libraryBookDocumentRepository.findByTitleAndLibraryId(title, libraryId, pageRequest)
                .map(BookDto::from);
    }

}

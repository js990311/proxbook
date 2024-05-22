package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.book.repository.BookDocumentRepository;
import com.proxbook.finder.domain.book.repository.document.BookDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticBookSearchService implements BookSearchService{
    private final BookDocumentRepository bookDocumentRepository;

    @Override
    public List<BookDto> readBookByTitle(String title) {
        return convert(bookDocumentRepository.findByTitleNoriOrTitleNgram(title, title));
    }

    private List<BookDto> convert(List<BookDocument> books){
        return books.stream().map(BookDto::from).toList();
    }
}

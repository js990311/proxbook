package com.proxbook.finder.service;

import com.proxbook.finder.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookSearchService {
    public List<BookDto> searchBooksByTitle(String title);
}

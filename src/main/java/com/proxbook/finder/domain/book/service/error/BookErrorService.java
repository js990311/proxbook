package com.proxbook.finder.domain.book.service.error;

import com.proxbook.finder.domain.book.dto.BookErrorLogDto;

import java.util.List;

public interface BookErrorService {
    List<BookErrorLogDto> readAllBookErrors();

    BookErrorLogDto readBookErrorByLogId(Long id);

    boolean deleteBookErrors(Long id);
}

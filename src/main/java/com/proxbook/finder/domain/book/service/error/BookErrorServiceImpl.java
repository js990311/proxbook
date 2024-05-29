package com.proxbook.finder.domain.book.service.error;

import com.proxbook.finder.domain.book.dto.BookErrorLogDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.entity.BookErrorLog;
import com.proxbook.finder.domain.book.repository.BookErrorLogRepository;
import com.proxbook.finder.domain.book.repository.BookRepository;
import com.proxbook.finder.domain.book.service.BookErrorRegistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BookErrorServiceImpl implements BookErrorRegistService, BookErrorService {
    private final BookErrorLogRepository bookErrorLogRepository;
    private final BookRepository bookRepository;

    @Override
    public void registBookError(Long bookId, String reason) {
        BookErrorLog log = BookErrorLog.builder()
                .setBookId(bookId)
                .setReason(reason)
                .build();
        bookErrorLogRepository.save(log);
    }

    @Override
    public List<BookErrorLogDto> readAllBookErrors() {
        List<BookErrorLog> bookErrorLog = bookErrorLogRepository.findAllWithBookBy();
        List<BookErrorLogDto> bookErrorLogDtos = bookErrorLog.stream().map(this::convert).toList();
        return bookErrorLogDtos;
    }

    @Override
    public BookErrorLogDto readBookErrorByLogId(Long id) {
        BookErrorLog bookErrorLog = bookErrorLogRepository.findWithBookById(id);
        return convert(bookErrorLog);
    }

    @Override
    public boolean deleteBookErrors(Long id){
        try {
            BookErrorLog bookErrorLog = bookErrorLogRepository.findWithBookById(id);
            Book book = bookErrorLog.getBook();

            bookErrorLogRepository.deleteByBookId(book.getId());
            bookRepository.delete(book);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private BookErrorLogDto convert(BookErrorLog bookErrorLog){
        return BookErrorLogDto.builder()
                .setId(bookErrorLog.getId())
                .setBook(bookErrorLog.getBook())
                .setCreatedAt(bookErrorLog.getCreatedAt())
                .setUpdatedAt(bookErrorLog.getUpdatedAt())
                .build();
    }
}

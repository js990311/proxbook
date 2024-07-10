package com.proxbook.finder.domain.book.service.error;

import com.proxbook.finder.domain.book.dto.BookErrorLogDto;
import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.book.entity.BookErrorLog;
import com.proxbook.finder.domain.book.repository.BookErrorLogRepository;
import com.proxbook.finder.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BookErrorService {
    private final BookErrorLogRepository bookErrorLogRepository;
    private final BookRepository bookRepository;

    public Long registBookError(Long bookId, String reason) {
        BookErrorLog log = BookErrorLog.builder()
                .setBookId(bookId)
                .setReason(reason)
                .build();
        bookErrorLogRepository.save(log);
        return log.getId();
    }

    public List<BookErrorLogDto> readAllBookErrors() {
        List<BookErrorLog> bookErrorLog = bookErrorLogRepository.findAllWithBookBy();
        List<BookErrorLogDto> bookErrorLogDtos = bookErrorLog.stream().map(this::convert).toList();
        return bookErrorLogDtos;
    }

    public BookErrorLogDto readBookErrorByLogId(Long id) {
        BookErrorLog bookErrorLog = bookErrorLogRepository.findWithBookById(id);
        return convert(bookErrorLog);
    }

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
                .setReason(bookErrorLog.getReason())
                .setCreatedAt(bookErrorLog.getCreatedAt())
                .setUpdatedAt(bookErrorLog.getUpdatedAt())
                .build();
    }
}

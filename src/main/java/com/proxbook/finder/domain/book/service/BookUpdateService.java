package com.proxbook.finder.domain.book.service;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.library.entity.Library;

/**
 * kakao book 검색 api를 사용하여 책의 정보를 찾아서 갱신하는 서비스
 */
public interface BookUpdateService {
    /**
     * entity의 내부 정보를 확인하여 update가 필요한 지 확인
     * @param bookId book entity의 id
     * @return update가 필요한 지 여부
     */
    public boolean needUpdate(Book book);

    public Book updateBook(Book book);
}

package com.proxbook.finder.domain.book.service.update;

import com.proxbook.finder.domain.book.dto.UpdateBookDto;

/**
 * Book entity에 대한 새로운 갱신 정보를 가져오는 인터페이스
 * kakao api or naver api or 크롤링 등의 방법으로
 */
public interface BookUpdateSourceService {
    /**
     * ISBN을 바탕으로 책의 갱신 정보를 가져다 주는 메서드
     * @param isbn 해당하는 책의 isbn
     * @return UpdateBookDto - 엔티티의 갱신작업에 사용되는 UpdateBookDto
     */
    public UpdateBookDto getBookSourceByIsbn(String isbn);
}

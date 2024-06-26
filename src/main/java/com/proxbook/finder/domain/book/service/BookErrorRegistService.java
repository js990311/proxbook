package com.proxbook.finder.domain.book.service;

/**
 * Book의 isbn 정보가 잘못되어 update source로부터 정보를 받아올 수 없는 경우 별도의 에러테이블로 해당 책의 정보를 저장 후 처리
 */
public interface BookErrorRegistService {
    public Long registBookError(Long bookId, String reason);
}

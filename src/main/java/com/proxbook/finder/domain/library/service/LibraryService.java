package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.entity.Library;

import java.util.List;

public interface LibraryService {
    /**
     * 책 ID를 기준으로 해당 책을 소장하는 가지는 도서관을 목록을 출력하는 메서드
     * @param bookId 책 ID
     * @return 책 소장 도서관
     */
    public List<LibraryDto> readLibraryByBookId(Long bookId);

    /**
     * 해당 도서관의 소장 도서를 가져옴
     * @param libraryId 도서관 id
     * @return
     */
    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId);

    /**
     * 도서관 소장도서 목록
     * @param libraryId 도서관 id
     * @param page
     * @return
     */
    public LibraryBookDto readLibraryBooksByLibraryId(Long libraryId, int page);

    /**
     * 해당 도서관에 책 제목에 걸맞는 도서가 있는지
     * @param libraryId 도서관 id
     * @param title 책 제목
     * @param page
     * @return
     */
    public LibraryBookDto readLibraryBooksByLibraryIdAndBookTitle(Long libraryId, String title, int page);

    /**
     * 도서관 이름으로 검색
     * @param libraryName 도서관 이름
     * @return 검색결과
     */
    public List<LibraryDto> readLibraryByLibraryName(String libraryName);

    /**
     * 도서관을 이름 혹은 도로명 주소로 검색하는 메서드
     * @param query 이름 혹은 주소에 사용할 검색어
     * @return 검색어가 서울인 경우 주소가 이름에 '서울' 주소에 '서울'이 들어가는 도서관을 반환함
     */
    public List<LibraryDto> readLibraryByLibraryNameOrAddress(String query);
}

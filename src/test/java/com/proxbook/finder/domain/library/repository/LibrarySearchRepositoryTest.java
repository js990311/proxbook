package com.proxbook.finder.domain.library.repository;

import com.proxbook.finder.domain.library.dto.LibraryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class LibrarySearchRepositoryTest {

    @Autowired private LibrarySearchRepository librarySearchRepository;

    @ParameterizedTest
    @MethodSource("provideLibraryCountByName")
    void findLibraryByName(String cityname, int count) {
        List<LibraryDto> libraries = librarySearchRepository.findLibraryByName(cityname);
        assertEquals(count, libraries.size());
    }

    @Test
    void findLibraryByNameOrAddress() {
    }

    @Test
    void findLibrariesByBookId() {
    }

    @Test
    void findLibrariesByIdList() {
    }

    static Stream<Arguments> provideLibraryCountByName(){
        return Stream.of(
                Arguments.of("서울", 24),
                Arguments.of("부산", 49),
                Arguments.of("대구", 22),
                Arguments.of("제주", 13)
        );
    }

}

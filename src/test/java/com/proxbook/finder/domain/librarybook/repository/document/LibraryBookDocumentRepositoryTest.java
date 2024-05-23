package com.proxbook.finder.domain.librarybook.repository.document;

import com.proxbook.finder.domain.librarybook.repository.LibraryBookDocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class LibraryBookDocumentRepositoryTest {

    @Autowired private LibraryBookDocumentRepository libraryBookDocumentRepository;

    @Test
    void findByTitleAndLibraryId() {
        List<LibraryBookDocument> books = libraryBookDocumentRepository.findByTitleAndLibraryId("고독", 4900);
        assertEquals(1, books.size());
        if(books.isEmpty())   fail();

    }

    @Test
    void findByLibraryId() {
        List<LibraryBookDocument> books = libraryBookDocumentRepository.findByLibraryId(4900);
        assertEquals(1573, books.size());
    }

    @Test
    void findByLibraryIdPage() {
        PageRequest pageRequest = PageRequest.of(
                0,20
        );
        Page<LibraryBookDocument> books = libraryBookDocumentRepository.findByLibraryId(4900, pageRequest);
        assertEquals(20, books.getNumberOfElements());
    }
    @Test
    void testFindByTitleAndLibraryId() {
        PageRequest pageRequest = PageRequest.of(
                0,20
        );
        Page<LibraryBookDocument> books = libraryBookDocumentRepository.findByTitleAndLibraryId("고독", 4900, pageRequest);
        assertEquals(1, books.getNumberOfElements());
    }
}
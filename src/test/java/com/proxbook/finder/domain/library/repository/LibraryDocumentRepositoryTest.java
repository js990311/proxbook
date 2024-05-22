package com.proxbook.finder.domain.library.repository;

import com.proxbook.finder.domain.library.repository.document.LibraryDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class LibraryDocumentRepositoryTest {

    @Autowired private LibraryDocumentRepository libraryDocumentRepository;

    @Test
    void findByNameNoriOrNameNgram() {
        List<LibraryDocument> libraries = libraryDocumentRepository.findByNameNoriOrNameNgram("서울", "서울");
        assertEquals(24, libraries.size());
    }
}
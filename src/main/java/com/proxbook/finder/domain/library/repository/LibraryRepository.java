package com.proxbook.finder.domain.library.repository;

import com.proxbook.finder.domain.library.entity.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query("SELECT l FROM Library l WHERE l.id IN :idList")
    public List<Library> findLibrariesByIdList(List<Long> idList);

    @Query("SELECT l FROM Library l WHERE l.id IN (SELECT lb.libraryId FROM LibraryBook lb WHERE lb.bookId = :bookId)")
    public Page<Library> findLibrariesPageByBookId(Long bookId, Pageable pageable);

    @Query("SELECT l FROM Library l WHERE l.id IN (SELECT lb.libraryId FROM LibraryBook lb WHERE lb.bookId = :bookId)")
    public List<Library> findLibrariesByBookId(Long bookId);


    @Query("SELECT l FROM Library l WHERE l.name LIKE concat('%', :name, '%')")
    public List<Library> findLibrariesByName(String name);
}

package com.proxbook.finder.domain.proxlibrary.repository;

import com.proxbook.finder.domain.book.entity.Book;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProxLibraryRepository extends JpaRepository<UserProxLibrary, Long> {
    @Query("SELECT up FROM UserProxLibrary up LEFT JOIN FETCH up.book WHERE up.id = :id")
    UserProxLibrary findFetchJoinBookById(Long id);
}

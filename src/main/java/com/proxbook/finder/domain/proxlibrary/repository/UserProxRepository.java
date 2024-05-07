package com.proxbook.finder.domain.proxlibrary.repository;

import com.proxbook.finder.domain.proxlibrary.entity.UserProx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProxRepository extends JpaRepository<UserProx, Long> {

    @Query("select up.dtype from UserProx up where up.id = :id")
    public String findDtypeById(Long id);
}

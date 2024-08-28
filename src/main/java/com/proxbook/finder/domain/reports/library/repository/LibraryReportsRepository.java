package com.proxbook.finder.domain.reports.library.repository;

import com.proxbook.finder.domain.reports.library.entity.LibraryReports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryReportsRepository extends JpaRepository<LibraryReports, Long> {
}

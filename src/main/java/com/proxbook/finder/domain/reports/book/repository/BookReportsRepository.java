package com.proxbook.finder.domain.reports.book.repository;

import com.proxbook.finder.domain.reports.book.entity.BookReports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReportsRepository extends JpaRepository<BookReports, Long> {
}

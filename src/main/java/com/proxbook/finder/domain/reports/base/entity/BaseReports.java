package com.proxbook.finder.domain.reports.base.entity;

import com.proxbook.finder.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseReports extends BaseTimeEntity {
    public BaseReports(ReportsType reportsType, String title, String message) {
        this.reportsType = reportsType;
        this.title = title;
        this.message = message;
    }

    @Enumerated(value = EnumType.STRING)
    @Column
    private ReportsType reportsType;

    @Column
    private String title;

    @Column
    private String message;
}

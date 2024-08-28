package com.proxbook.finder.domain.reports.api.response;

import com.proxbook.finder.domain.reports.base.entity.ReportsType;
import lombok.Data;

@Data
public class BaseReportsResponse {
    private Long id;
    private String title;
    private String message;
    private ReportsType reportsType;

    public BaseReportsResponse(Long id, String title, String message, ReportsType reportsType) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.reportsType = reportsType;
    }
}

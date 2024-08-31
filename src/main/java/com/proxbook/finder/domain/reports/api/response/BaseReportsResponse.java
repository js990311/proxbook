package com.proxbook.finder.domain.reports.api.response;

import com.proxbook.finder.domain.reports.base.entity.ReportsType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "문의에 대한 응답")
@Data
public class BaseReportsResponse {
    @Schema(description = "문의에 대한 ID")
    private Long id;
    @Schema(description = "문의 제목")
    private String title;
    @Schema(description = "문의 내용")
    private String message;
    @Schema(description = "문의 유형")
    private ReportsType reportsType;

    public BaseReportsResponse(Long id, String title, String message, ReportsType reportsType) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.reportsType = reportsType;
    }
}

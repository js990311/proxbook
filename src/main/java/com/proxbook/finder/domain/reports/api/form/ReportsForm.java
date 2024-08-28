package com.proxbook.finder.domain.reports.api.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReportsForm {
    @Schema(description = "문의 제목", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String title;

    @Schema(description = "문의 내용(본문)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String message;
}

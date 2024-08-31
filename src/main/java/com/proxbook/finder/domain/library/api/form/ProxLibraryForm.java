package com.proxbook.finder.domain.library.api.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "내 인근 도서관 검색에 대한 Form")
@Data
public class ProxLibraryForm {
    @Schema(description = "현재 위치에 대한 경도", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private double latitude;
    @Schema(description = "현재 위치에 대한 위도", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private double longitude;
    @Schema(description = "검색할 범위(단위는 m)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(100)
    @Max(10_000)
    private Long range;

    public double kmRange(){
        return (double) range / 1000;
    }
}

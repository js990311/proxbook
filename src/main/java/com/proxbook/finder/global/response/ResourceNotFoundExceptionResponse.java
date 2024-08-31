package com.proxbook.finder.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "404를 위한 Exception 설명 객체")
@Data
public class ResourceNotFoundExceptionResponse {
    @Schema(description = "찾지 못한 리소스의 id")
    private Long resourceId;

    @Schema(description = "찾지못한 리소스의 타입")
    private ResourceType resourceType;

    @Schema(description = "부가설명")
    private String message;

    public ResourceNotFoundExceptionResponse(Long resourceId, ResourceType resourceType, String message) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.message = message;
    }

    public enum ResourceType{
        BOOK, LIBRARY
    }
}

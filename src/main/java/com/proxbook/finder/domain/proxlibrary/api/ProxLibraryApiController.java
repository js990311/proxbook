package com.proxbook.finder.domain.proxlibrary.api;

import com.proxbook.finder.domain.library.api.form.ProxLibraryForm;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import com.proxbook.finder.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ProxLibraryApiController {
    private final UserProxLibraryService userProxLibraryService;

    @Operation(summary = "사전에 검색한 내 주변 도서관 다시보기")
    @GetMapping("/prox/{url}")
    public BaseResponse<UserProxLibraryDto> getUserProxLibrary(
            @Parameter(description = "shorten-url")
            @PathVariable("url") String url
    ){
        return new BaseResponse<>(userProxLibraryService.readUserProxLibraryByShortenUrl(url));
    }

    @Operation(summary = "내 주변에 이 책을 소장하는 도서관 검색")
    @PostMapping("/api/book/{id}/prox")
    public BaseResponse<UserProxLibraryDto> getProxLibraryBook(
            @Parameter(description = "책 ID(ISBN)")
            @PathVariable("id") Long id, @RequestBody ProxLibraryForm form){
        return new BaseResponse<>(userProxLibraryService.createUserProxLibraryByBookIdAndGeo(id, form.getLatitude(), form.getLongitude(), form.getRange()));
    }

    @Operation(summary = "내 주변 도서관 검색")
    @PostMapping("/api/library/prox")
    public BaseResponse<UserProxLibraryDto> getProxLibraryBook(@RequestBody ProxLibraryForm form){
        return new BaseResponse<>(userProxLibraryService.createUserProxLibraryByGeo(form.getLatitude(), form.getLongitude(), form.getRange()));
    }

}

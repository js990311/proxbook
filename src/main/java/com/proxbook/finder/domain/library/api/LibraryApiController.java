package com.proxbook.finder.domain.library.api;

import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import com.proxbook.finder.global.response.BaseListResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/library")
@RestController
public class LibraryApiController {
    private final LibraryService libraryService;
    private final UserProxLibraryService userProxLibraryService;

    @Operation(summary = "도서관을 이름으로 검색")
    @GetMapping
    public BaseListResponse<LibraryDto> search(@RequestParam("name") String name){
        return new BaseListResponse<>(libraryService.readLibraryByLibraryName(name));
    }

    @Operation(summary = "도서관 id로 도서관 검색")
    @GetMapping("/{id}")
    public LibraryDto findByLibraryId(@RequestParam("id") Long id){
        return libraryService.findByLibraryId(id);
    }

    @Operation(summary = "도서관 소장도서 검색")
    @GetMapping("/{id}/book")
    public LibraryBookDto findLibraryBookByLibraryId(@RequestParam("id") Long id){
         return libraryService.readLibraryBooksByLibraryId(id);
    }

    @Operation(summary = "내 주변 도서관 검색")
    @PostMapping("/prox")
    public UserProxLibraryDto getProxLibraryBook(@RequestBody LibraryForm form){
        return userProxLibraryService.createUserProxLibraryByGeo(form.getLatitude(), form.getLongitude(), 10.0);
    }

}

package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibrarySearchDto;
import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.service.LibrarySearchService;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/library")
@Controller
public class LibraryController {
    private final UserProxLibraryService userProxLibraryService;
    private final LibraryService libraryService;
    private final LibrarySearchService librarySearchService;

    @GetMapping("/prox-library")
    public String getProxLibrary(){
        return "prox-libraries";
    }

    @PostMapping("/prox-library")
    public String postProxLibrary(@RequestBody LibraryForm form, Model model){
        UserProxLibraryDto userProxLibraryDto = userProxLibraryService.createUserProxLibraryByGeo(
                form.getLatitude(),
                form.getLongitude(),
                10.0 // range는 추후 추가
        );
        model.addAttribute("userProxLibrary", userProxLibraryDto);
        model.addAttribute("libraries", userProxLibraryDto.getLibraries());
        return "fragments/prox-library";
    }

    @GetMapping("/search")
    public String getBookSearch(@RequestParam(value = "name", required = false) String name, Model model){
        if(name!=null) {
            List<LibraryDto> libraries = librarySearchService.readLibraryByLibraryName(name);
            LibrarySearchDto searchedLibraries = LibrarySearchDto.builder()
                    .setLibraries(libraries)
                    .build();
            model.addAttribute("searchedLibraries", searchedLibraries);
        }
        return "librarySearch";
    }

    @GetMapping("/{id}")
    public String getLibraryBook(@PathVariable("id") Long id,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 Model model){
        LibraryBookDto libraryBooks = libraryService.readLibraryBooksByLibraryId(id,page);

        model.addAttribute("libraryBooks", libraryBooks);
        return "library-book";
    }
}

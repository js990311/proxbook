package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.application.form.LibrarySearchForm;
import com.proxbook.finder.application.form.LibrarySearchOption;
import com.proxbook.finder.domain.library.dto.LibraryBookDto;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibrarySearchDto;
import com.proxbook.finder.domain.library.service.LibraryService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/library")
@Controller
public class LibraryController {
    private final UserProxLibraryService userProxLibraryService;
    private final LibraryService libraryService;

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
    public String getLibrarySearch(@ModelAttribute(name = "form") LibrarySearchForm form, Model model){
        if(form.getName()!=null) {
            List<LibraryDto> libraries;
            if(form.getOption().equals(LibrarySearchOption.NAME)){
                libraries = libraryService.readLibraryByLibraryName(form.getName());
            }else if(form.getOption().equals(LibrarySearchOption.ADDRESS)){
                libraries = libraryService.readLibraryByLibraryNameOrAddress(form.getName());
            }else if(form.getOption().equals(LibrarySearchOption.BOTH)){
                libraries = libraryService.readLibraryByLibraryNameOrAddress(form.getName());
            }else {
                libraries = new ArrayList<>();
            }
            LibrarySearchDto searchedLibraries = LibrarySearchDto.builder()
                    .setLibraries(libraries)
                    .build();
            model.addAttribute("searchedLibraries", searchedLibraries);
            model.addAttribute("form", form);
        }else {
            model.addAttribute("form", new LibrarySearchForm());
        }
        model.addAttribute("options", LibrarySearchOption.values());
        return "librarySearch";
    }

    @GetMapping("/{id}")
    public String getLibraryBook(@PathVariable("id") Long id,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 Model model){
        int pageIdx = page <= 0 ? 0 : page-1;
        if(title == null){
            LibraryBookDto libraryBooks = libraryService.readLibraryBooksByLibraryId(id,pageIdx);
            model.addAttribute("libraryBooks", libraryBooks);
        }else {
            LibraryBookDto libraryBooks = libraryService.readLibraryBooksByLibraryIdAndBookTitle(id,title, pageIdx);
            model.addAttribute("libraryBooks", libraryBooks);
            model.addAttribute("title", title);
        }
        return "library-book";
    }
}

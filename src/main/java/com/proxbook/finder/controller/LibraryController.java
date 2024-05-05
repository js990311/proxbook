package com.proxbook.finder.controller;

import com.proxbook.finder.controller.form.LibraryBookForm;
import com.proxbook.finder.controller.form.LibraryForm;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.service.LibrarySearchService;
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
    private final LibrarySearchService librarySearchService;

    @GetMapping("/prox-library")
    public String getProxLibrary(){
        return "prox-libraries";
    }

    @PostMapping("/prox-library")
    public String proxLibrary(@RequestBody LibraryForm form, Model model){
        List<LibraryDto> libraries = librarySearchService.searchLibraryByGeo(form.getLatitude(),form.getLongitude());
        model.addAttribute("libraries", libraries);
        return "fragments/libraries";
    }

    @PostMapping("/prox-book")
    public String proxBook(@RequestBody LibraryBookForm form, Model model){
        List<LibraryDto> libraries = librarySearchService.searchLibraryByGeoAndBookId(
                form.getBookId(),
                form.getLatitude(),
                form.getLongitude()
        );
        model.addAttribute("libraries", libraries);
        return "fragments/libraries";
    }
}

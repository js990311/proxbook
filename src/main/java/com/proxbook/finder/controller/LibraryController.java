package com.proxbook.finder.controller;

import com.proxbook.finder.controller.form.LibraryBookForm;
import com.proxbook.finder.dto.LibraryDto;
import com.proxbook.finder.service.LibrarySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/library")
@Controller
public class LibraryController {
    private final LibrarySearchService librarySearchService;

    @PostMapping("/proxbook")
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

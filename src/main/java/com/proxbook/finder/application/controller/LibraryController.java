package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryBookForm;
import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.application.form.LibraryGeoForm;
import com.proxbook.finder.application.service.UserProxLibrarySearchService;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryGeoDto;
import com.proxbook.finder.application.service.LibrarySearchService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
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
    private final UserProxLibrarySearchService userProxLibrarySearchService;

    @GetMapping("/prox-library")
    public String getProxLibrary(){
        return "prox-libraries";
    }

    @PostMapping("/prox-library")
    public String proxLibrary(@RequestBody LibraryForm form, Model model, HttpSession session){
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.saveUserProxLibrary(
                form.getLatitude(),
                form.getLongitude(),
                10.0
        );
        model.addAttribute("userProxLibrary", userProxLibraryDto);
        model.addAttribute("libraries", userProxLibraryDto.getLibraries());
        return "fragments/prox-library";
    }
}

package com.proxbook.finder.controller;

import com.proxbook.finder.controller.form.LibraryBookForm;
import com.proxbook.finder.controller.form.LibraryForm;
import com.proxbook.finder.controller.form.LibraryGeoForm;
import com.proxbook.finder.domain.library.dto.LibraryDto;
import com.proxbook.finder.domain.library.dto.LibraryGeoDto;
import com.proxbook.finder.service.LibrarySearchService;
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

    @GetMapping("/prox-library")
    public String getProxLibrary(){
        return "prox-libraries";
    }

    @PostMapping("/prox-library")
    public String proxLibrary(@RequestBody LibraryForm form, Model model, HttpSession session){
        List<LibraryDto> libraries = librarySearchService.searchLibraryByGeo(form.getLatitude(),form.getLongitude());
        model.addAttribute("libraries", libraries);
        session.setAttribute("prox-libraries", libraries);
        return "fragments/libraries";
    }

    @ResponseBody
    @PostMapping("/prox-library/geo")
    public GeoLibraryMapper proxLibraryGet(@RequestBody LibraryGeoForm form){
        List<String> libraryCodes = form.getLibraryCodes();
        List<LibraryGeoDto> libraryGeos = librarySearchService.searchLibraryGeoByLibraryCodes(libraryCodes);
        GeoLibraryMapper ret = new GeoLibraryMapper(libraryGeos);
        return ret;
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

    @Getter
    static class GeoLibraryMapper{
        private List<LibraryGeoDto> libraries;
        private int count;

        public GeoLibraryMapper(List<LibraryGeoDto> libraries) {
            if(libraries == null){
                this.libraries = null;
                this.count = 0;
            }else {
                this.libraries = libraries;
                this.count = libraries.size();
            }
        }
    }
}

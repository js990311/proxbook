package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryBookForm;
import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.application.service.UserProxSearchService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxBookLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserProxController {
    private final UserProxSearchService userProxSearchService;

    @ResponseBody
    @PostMapping("/prox-library/create")
    public UserProxLibraryDto saveUserProxLibrary(@RequestBody LibraryForm form){
        UserProxLibraryDto userProxLibraryDto = userProxSearchService.saveUserProxLibrary(
                form.getLatitude(),
                form.getLongitude(),
                10.0
        );
        return userProxLibraryDto;
    }

    @ResponseBody
    @PostMapping("/prox-book/create")
    public UserProxLibraryDto proxBook(@RequestBody LibraryBookForm form, Model model){
        UserProxLibraryDto userProxLibraryDto = userProxSearchService.saveUserProxBookLibrary(
            form.getBookId(),
            form.getLatitude(),
            form.getLongitude(),
            10.0
        );
        return userProxLibraryDto;
    }


    @ResponseBody
    @GetMapping("/s/{shorten-url}")
    public UserProxLibraryDto getUserProxLibrary(@PathVariable("shorten-url") String url){
        UserProxLibraryDto userProxLibraryDto = userProxSearchService.findUserProxLibraryByShortenUrl(url);
        return userProxLibraryDto;
    }
}


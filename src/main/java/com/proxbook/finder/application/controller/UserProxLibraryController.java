package com.proxbook.finder.application.controller;

import com.proxbook.finder.aop.annotation.MethodTimeChecker;
import com.proxbook.finder.application.form.LibraryBookForm;
import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.application.service.UserProxLibrarySearchService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserProxLibraryController {
    private final UserProxLibrarySearchService userProxLibrarySearchService;

    @MethodTimeChecker
    @ResponseBody
    @PostMapping("/prox-library/create")
    public UserProxLibraryDto createProxLibrary(@RequestBody LibraryForm form){
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.saveUserProxLibrary(
                form.getLatitude(),
                form.getLongitude(),
                10.0
        );
        return userProxLibraryDto;
    }

    @MethodTimeChecker
    @ResponseBody
    @PostMapping("/prox-book/create")
    public UserProxLibraryDto createProxBook(@RequestBody LibraryBookForm form, Model model){
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.saveUserProxLibraryByBook(
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
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.findUserProxLibraryByShortenUrl(url);
        return userProxLibraryDto;
    }
}


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

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/s/{shorten-url}")
    public String getUserProxLibrary(@PathVariable("shorten-url") String url, Model model){
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.findUserProxLibraryByShortenUrl(url);
        model.addAttribute("userProxLibrary", userProxLibraryDto);
        return "user-prox-library";
    }
}


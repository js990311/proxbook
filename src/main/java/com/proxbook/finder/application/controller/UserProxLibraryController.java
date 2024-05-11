package com.proxbook.finder.application.controller;

import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserProxLibraryController {
    private final UserProxLibraryService userProxLibraryService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/s/{shorten-url}")
    public String getUserProxLibrary(@PathVariable("shorten-url") String url, Model model){
        UserProxLibraryDto userProxLibraryDto = userProxLibraryService.findUserProxLibraryByShortenUrl(url);
        model.addAttribute("userProxLibrary", userProxLibraryDto);
        return "user-prox-library";
    }
}


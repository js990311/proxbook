package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.application.service.UserProxLibrarySearchService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserProxLibraryController {
    private final UserProxLibrarySearchService userProxLibrarySearchService;

    @ResponseBody
    @PostMapping("/prox-library/create")
    public UserProxLibraryDto saveUserProxLibrary(@RequestBody LibraryForm form){
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.saveUserProxLibrary(
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

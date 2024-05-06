package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.application.service.UserProxLibrarySearchService;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/prox-library")
public class UserProxLibraryController {
    private final UserProxLibrarySearchService userProxLibrarySearchService;

    @ResponseBody
    @PostMapping("/create")
    public UserProxLibraryDto saveUserProxLibrary(@RequestBody LibraryForm form){
        UserProxLibraryDto userProxLibraryDto = userProxLibrarySearchService.saveUserProxLibrary(
                form.getLatitude(),
                form.getLongitude(),
                10.0
        );
        return userProxLibraryDto;
    }
}

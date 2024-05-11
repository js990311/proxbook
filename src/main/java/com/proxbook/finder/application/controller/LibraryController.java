package com.proxbook.finder.application.controller;

import com.proxbook.finder.application.form.LibraryForm;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.service.UserProxLibraryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/library")
@Controller
public class LibraryController {
    private final UserProxLibraryService userProxLibraryService;

    @GetMapping("/prox-library")
    public String getProxLibrary(){
        return "prox-libraries";
    }

    @PostMapping("/prox-library")
    public String proxLibrary(@RequestBody LibraryForm form, Model model, HttpSession session){
        UserProxLibraryDto userProxLibraryDto = userProxLibraryService.saveUserProxLibraryByGeo(
                form.getLatitude(),
                form.getLongitude(),
                10.0 // range는 추후 추가
        );
        model.addAttribute("userProxLibrary", userProxLibraryDto);
        model.addAttribute("libraries", userProxLibraryDto.getLibraries());
        return "fragments/prox-library";
    }
}

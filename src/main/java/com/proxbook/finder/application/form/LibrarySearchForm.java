package com.proxbook.finder.application.form;

import com.proxbook.finder.domain.library.api.form.LibrarySearchOption;
import lombok.Data;

@Data
public class LibrarySearchForm {
    private String name;
    private LibrarySearchOption option;

    public LibrarySearchForm() {
        option = LibrarySearchOption.NAME;
    }
}

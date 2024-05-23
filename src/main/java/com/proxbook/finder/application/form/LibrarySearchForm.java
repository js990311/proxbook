package com.proxbook.finder.application.form;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class LibrarySearchForm {
    private String name;
    private LibrarySearchOption option;

    public LibrarySearchForm() {
        option = LibrarySearchOption.NAME;
    }
}

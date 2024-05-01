package com.proxbook.finder.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LibraryBookForm {
    private String bookId;
    private double latitude;
    private double longitude;
}

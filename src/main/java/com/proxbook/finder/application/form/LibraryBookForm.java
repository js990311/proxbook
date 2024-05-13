package com.proxbook.finder.application.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LibraryBookForm {
    private Long bookId;
    private double latitude;
    private double longitude;
}

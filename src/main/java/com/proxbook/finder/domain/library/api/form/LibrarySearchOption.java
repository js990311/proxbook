package com.proxbook.finder.domain.library.api.form;

public enum LibrarySearchOption {
    NAME("이름"), ADDRESS("주소"), BOTH("이름과 주소");

    private final String description;

    LibrarySearchOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

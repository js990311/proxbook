package com.proxbook.finder.domain.library.service;

import com.proxbook.finder.domain.library.entity.Library;

import java.util.List;

public interface LibraryService {
    public List<Library> findByGeo(double latitude, double longtitude, double distance_range);
}

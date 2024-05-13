package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;

import java.util.List;

public interface ProxLibraryService {

    public List<ProxLibrary> createProxLibraryByGeo(double latitude, double longitude, double range);
    public List<ProxLibrary> createProxLibraryByBookIdAndGeo(Long bookId, double latitude, double longitude, double range);

    public List<ProxLibrary> readProxLibraryByUserProxLibraryId(Long id);
}

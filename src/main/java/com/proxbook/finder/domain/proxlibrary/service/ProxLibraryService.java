package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.proxlibrary.entity.ProxLibrary;

import java.util.List;

public interface ProxLibraryService {

    public List<ProxLibrary.Builder> saveProxLibraryByGeo(double latitude, double longitude, double range);
}

package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;

public interface UserProxLibraryService {

    public UserProxLibrary saveUserProxLibraryByGeo(double latitude, double longitude, double range);
}

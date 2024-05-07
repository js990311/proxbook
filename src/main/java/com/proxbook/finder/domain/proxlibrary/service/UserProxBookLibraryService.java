package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.proxlibrary.entity.UserProxBookLibrary;

public interface UserProxBookLibraryService {
    public UserProxBookLibrary saveUserProxBookLibraryByBookIdAndGeo(String bookId, double latitude, double longitude, double range);
}

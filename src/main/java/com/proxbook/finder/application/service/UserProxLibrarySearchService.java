package com.proxbook.finder.application.service;

import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;

public interface UserProxLibrarySearchService {
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double distance);

    public UserProxLibraryDto findUserProxLibraryById(Long id);
}

package com.proxbook.finder.application.service;

import com.proxbook.finder.domain.proxlibrary.dto.UserProxBookLibraryDto;
import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;

public interface UserProxSearchService {
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double range);

    public UserProxBookLibraryDto saveUserProxBookLibrary(String bookId, double latitude, double longitude, double range);

    public UserProxLibraryDto findUserProxLibraryById(Long id);

    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url);
}

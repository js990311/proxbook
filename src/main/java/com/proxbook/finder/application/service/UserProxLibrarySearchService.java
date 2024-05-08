package com.proxbook.finder.application.service;

import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;

public interface UserProxLibrarySearchService {
    public UserProxLibraryDto saveUserProxLibrary(double latitude, double longitude, double range);

    public UserProxLibraryDto saveUserProxLibraryByBook(String bookId, double latitude, double longitude, double range);

    public UserProxLibraryDto findUserProxLibraryById(Long id);

    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url);
}

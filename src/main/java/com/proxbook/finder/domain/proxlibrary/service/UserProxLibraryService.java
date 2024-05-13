package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;

public interface UserProxLibraryService {

    public UserProxLibraryDto createUserProxLibraryByGeo(double latitude, double longitude, double range);

    public UserProxLibraryDto createUserProxLibraryByBookIdAndGeo(Long bookId, double latitude, double longitude, double range);

    public UserProxLibraryDto readUserProxLibraryByShortenUrl(String url);
}

package com.proxbook.finder.domain.proxlibrary.service;

import com.proxbook.finder.domain.proxlibrary.dto.UserProxLibraryDto;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;

public interface UserProxLibraryService {

    public UserProxLibraryDto saveUserProxLibraryByGeo(double latitude, double longitude, double range);

    public UserProxLibraryDto saveUserProxLibraryByBookIdAndGeo(String bookId, double latitude, double longitude, double range);

    public UserProxLibraryDto findUserProxLibraryByShortenUrl(String url);
}

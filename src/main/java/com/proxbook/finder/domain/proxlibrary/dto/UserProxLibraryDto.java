package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.application.service.utils.Base62Encoder;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserProxLibraryDto {
    private String url;
    private List<ProxLibraryDto> libraries;
    private int count;

    public UserProxLibraryDto(UserProxLibrary userProxLibrary){
        this.url = Base62Encoder.encode(userProxLibrary.getId());
        this.libraries = userProxLibrary.getProxLibraries().stream().map(ProxLibraryDto::new).toList();
        if(this.libraries != null)
            this.count = libraries.size();
        else
            this.count = 0;
    }

    public UserProxLibraryDto(String url, List<ProxLibraryDto> libraries) {
        this.url = url;
        this.libraries = libraries;
        if(this.libraries != null)
            this.count = libraries.size();
        else
            this.count = 0;
    }
}

package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import lombok.Getter;

import java.util.List;

@Getter
public class UserProxLibraryDto {
    private Long id;
    private List<ProxLibraryDto> libraries;
    private int count;

    public UserProxLibraryDto(UserProxLibrary userProxLibrary){
        this.id = userProxLibrary.getId();
        this.libraries = userProxLibrary.getProxLibraries().stream().map(ProxLibraryDto::new).toList();
        if(this.libraries != null)
            this.count = libraries.size();
        else
            this.count = 0;
    }

}

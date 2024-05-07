package com.proxbook.finder.domain.proxlibrary.dto;

import com.proxbook.finder.domain.book.dto.BookDto;
import com.proxbook.finder.domain.proxlibrary.entity.UserProx;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxBookLibrary;
import com.proxbook.finder.domain.proxlibrary.entity.UserProxLibrary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class UserProxBookLibraryDto extends UserProxLibraryDto {
    private BookDto bookDto;

    public UserProxBookLibraryDto(UserProxBookLibrary userProxBookLibrary) {
        super((UserProx) userProxBookLibrary);
        this.bookDto = new BookDto(userProxBookLibrary.getBook());
    }

    public UserProxBookLibraryDto(String url, List<ProxLibraryDto> libraries) {
        super(url, libraries);
    }
}

package com.proxbook.finder.domain.library.dto;

import com.proxbook.finder.domain.library.entity.Library;
import com.proxbook.finder.domain.library.opensearch.document.LibraryDocument;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LibraryDto {
    @Schema(description = "API에서 사용하는 LibraryId(도서관 ID)와 동일한 의미를 가짐")
    private Long libraryCode;
    private String name;
    private String address;
    private String webpage;
    private Double latitude;
    private Double longitude;

    public LibraryDto(Long libraryCode, String name, String address, String webpage, Double latitude, Double longitude) {
        this.libraryCode = libraryCode;
        this.name = name;
        this.address = address;
        this.webpage = webpage;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static LibraryDto.Builder builder(){
        return new Builder();
    }

    public static LibraryDto from(Library library){
        return new Builder()
                .setLibraryCode(library.getId())
                .setName(library.getName())
                .setWebpage(library.getWebpage())
                .setAddress(library.getAddress())
                .setLatitude(library.getLatitude())
                .setLongitude(library.getLongitude())
                .build();
    }

    public static LibraryDto from(LibraryDocument library){
        return new Builder()
                .setLibraryCode(library.getLibararyId())
                .setName(library.getName())
                .setWebpage(library.getWebpage())
                .setAddress(library.getAddress())
                .setLatitude(library.getLatitude())
                .setLongitude(library.getLongitude())
                .build();
    }


    public static class Builder{
        private Long libraryCode;
        private String name;
        private String address;
        private String webpage;
        private Double latitude;
        private Double longitude;

        public LibraryDto build(){
            return new LibraryDto(
                    libraryCode,
                    name,
                    address,
                    webpage,
                    latitude,
                    longitude
            );
        }

        public Builder setLibraryCode(Long libraryCode) {
            this.libraryCode = libraryCode;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setWebpage(String webpage) {
            this.webpage = webpage;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }
    }
}

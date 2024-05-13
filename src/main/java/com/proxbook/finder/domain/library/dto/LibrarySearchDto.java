package com.proxbook.finder.domain.library.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class LibrarySearchDto {
    private Integer count;
    private List<LibraryDto> libraries;
    private Double latitude;
    private Double longitude;

    public LibrarySearchDto(Integer count, List<LibraryDto> libraries, Double latitude, Double longitude) {
        this.count = count;
        this.libraries = libraries;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Integer count;
        private List<LibraryDto> libraries;
        private Double latitude;
        private Double longitude;

        public LibrarySearchDto build(){
            if(latitude == null)
                latitude = 37.57;
            if(longitude == null)
                longitude = 126.97;

            return new LibrarySearchDto(
                    count,
                    libraries,
                    latitude,
                    longitude
            );
        }

        public Builder setCount(Integer count) {
            this.count = count;
            return this;
        }

        public Builder setLibraries(List<LibraryDto> libraries) {
            this.libraries = libraries;
            if(libraries!=null)
                this.count = libraries.size();
            else
                this.count = 0;
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

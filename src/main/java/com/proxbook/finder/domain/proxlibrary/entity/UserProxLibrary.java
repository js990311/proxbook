package com.proxbook.finder.domain.proxlibrary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 유저의 현재 위치에서 가장 까가운 도서관 목록 모음
 */
@Entity
@NoArgsConstructor
@DiscriminatorValue("library")
public class UserProxLibrary extends UserProx{
}

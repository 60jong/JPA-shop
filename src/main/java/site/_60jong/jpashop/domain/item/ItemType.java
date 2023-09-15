package site._60jong.jpashop.domain.item;

import lombok.Getter;

@Getter
public enum ItemType {
    MOVIE("영화"), BOOK("도서"), ETC("기타");

    private String description;

    ItemType(String description) {
        this.description = description;
    }
}

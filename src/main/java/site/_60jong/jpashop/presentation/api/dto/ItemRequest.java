package site._60jong.jpashop.presentation.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import site._60jong.jpashop.domain.item.Book;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.domain.item.ItemType;
import site._60jong.jpashop.domain.item.Movie;

@Getter(AccessLevel.PRIVATE) @Setter
public abstract class ItemRequest {
    private String name;
    private int price;
    private int stockQuantity;
    private ItemType type;

    public abstract Item toEntity();

    @Setter
    public static class MovieRequest extends ItemRequest {
        private String director;
        private String actor;

        @Override
        public Item toEntity() {
            return new Movie(super.getName(), super.getPrice(), super.getStockQuantity(), director, actor);
        }
    }

    @Setter
    public static class BookRequest extends ItemRequest {
        private String author;
        private String isbn;

        @Override
        public Item toEntity() {
            return new Book(super.getName(), super.getPrice(), super.getStockQuantity(), author, isbn);
        }
    }

}

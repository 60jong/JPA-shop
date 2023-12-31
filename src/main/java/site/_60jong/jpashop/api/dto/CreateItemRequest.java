package site._60jong.jpashop.api.dto;

import lombok.Getter;
import lombok.Setter;
import site._60jong.jpashop.domain.item.Book;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.domain.item.ItemType;
import site._60jong.jpashop.domain.item.Movie;

@Getter @Setter
public abstract class CreateItemRequest {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private ItemType type;

    public abstract Item toEntity();

    @Setter
    public static class MovieForm extends CreateItemRequest {
        private String director;
        private String actor;

        @Override
        public Item toEntity() {
            return new Movie(super.getName(), super.getPrice(), super.getStockQuantity(), director, actor);
        }
    }

    @Getter @Setter
    public static class BookForm extends CreateItemRequest {
        private String author;
        private String isbn;

        @Override
        public Item toEntity() {
            return new Book(super.getName(), super.getPrice(), super.getStockQuantity(), author, isbn);
        }
    }

}

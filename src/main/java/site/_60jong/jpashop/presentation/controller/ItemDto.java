package site._60jong.jpashop.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import site._60jong.jpashop.domain.item.Item;

@Getter @Setter
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public static ItemDto toDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity());
    }
}

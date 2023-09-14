package site._60jong.jpashop.presentation.api.dto;

import site._60jong.jpashop.domain.item.Item;

public class ItemSearchResponse {
    private String name;
    private int price;
    private int stockQuantity;

    private ItemSearchResponse(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public static ItemSearchResponse toDto(Item item) {
        return new ItemSearchResponse(item.getName(), item.getPrice(), item.getStockQuantity());
    }
}

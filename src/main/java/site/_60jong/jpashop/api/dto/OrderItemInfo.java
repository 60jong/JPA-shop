package site._60jong.jpashop.api.dto;

import lombok.Getter;
import site._60jong.jpashop.domain.OrderItem;
import site._60jong.jpashop.domain.item.Item;

@Getter
public class OrderItemInfo {

    private Long itemId;
    private String itemName;
    private int count;
    private int price;
    private int stockQuantity;
    private int itemTotalPrice;

    public OrderItemInfo(OrderItem orderItem) {
        Item item = orderItem.getItem();
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.count = orderItem.getCount();
        this.itemTotalPrice = count * price;
    }
}

package site._60jong.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site._60jong.jpashop.application.service.OrderItemInformation;
import site._60jong.jpashop.domain.item.Item;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public OrderItem(Item item, int count) {
        this.item = item;
        item.addOrderItem(this);
        item.removeStoke(count);
    }

    public int getTotalPrice() {
        return item.getPrice() * count;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

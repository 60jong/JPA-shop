package site._60jong.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site._60jong.jpashop.domain.OrderItem;
import site._60jong.jpashop.domain.category.Category;
import site._60jong.jpashop.domain.category.ItemCategory;
import site._60jong.jpashop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<ItemCategory> itemCategories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    // Constructor
    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    //== 비즈니스 로직 ==//
    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStoke(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    public void addCategory(Category category) {
        ItemCategory itemCategory = new ItemCategory(this, category);
        this.itemCategories.add(itemCategory);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
}

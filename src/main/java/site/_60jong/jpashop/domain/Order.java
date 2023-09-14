package site._60jong.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site._60jong.jpashop.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ORDER;

    // Constructor
    public Order(Member member, Item... items) {
        this.member = member;
        member.addOrder(this);
        Arrays.stream(items)
                .forEach(this::addItem);
    }

    //== 비즈니스 로직 ==//
    public void addItem(Item item) {
        OrderItem orderItem = new OrderItem(this, item);
        this.orderItems.add(orderItem);
    }

    public void cancel() {
        status = OrderStatus.CANCEL;
    }
}

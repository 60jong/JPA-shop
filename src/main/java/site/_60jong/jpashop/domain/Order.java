package site._60jong.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site._60jong.jpashop.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime orderDateTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ORDER;

    // Constructor
    public Order(Member member, List<OrderItem> orderItems) {
        this.member = member;
        member.addOrder(this);

        this.orderDateTime = LocalDateTime.now();

        orderItems.stream()
                .forEach(orderItem -> {
                    this.orderItems.add(orderItem);
                    orderItem.setOrder(this);
                });
    }

    //== 비즈니스 로직 ==//
    public void cancel() {
        status = OrderStatus.CANCEL;
    }

}

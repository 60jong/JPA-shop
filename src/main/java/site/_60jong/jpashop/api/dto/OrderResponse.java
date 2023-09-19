package site._60jong.jpashop.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import site._60jong.jpashop.domain.Address;
import site._60jong.jpashop.domain.Member;
import site._60jong.jpashop.domain.Order;
import site._60jong.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponse {

    private Long orderId;
    private MemberInfo memberInfo;
    private OrderItemInfos orderItemInfos;
    private LocalDateTime orderDateTime;
    private OrderStatus status;
    private Address address;

    public OrderResponse(Order order) {
        Member member = order.getMember();
        this.orderId = order.getId();
        this.memberInfo = new MemberInfo(member);
        this.orderItemInfos = new OrderItemInfos(order);
        this.orderDateTime = order.getOrderDateTime();
        this.status = order.getStatus();
        this.address = order.getDelivery().getAddress();
    }
}

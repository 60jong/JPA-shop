package site._60jong.jpashop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site._60jong.jpashop.domain.Address;
import site._60jong.jpashop.domain.Member;
import site._60jong.jpashop.domain.Order;
import site._60jong.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;

@Getter
public class SimpleOrderResponse {

    private Long orderId;
    private String memberName;
    private LocalDateTime orderDateTime;
    private OrderStatus status;
    private Address address;

    public SimpleOrderResponse(Order order) {
        Member orderMember = order.getMember();
        this.orderId = order.getId();
        this.memberName = orderMember.getUsername();
        this.orderDateTime = order.getOrderDateTime();
        this.status = order.getStatus();
        this.address = orderMember.getAddress();
    }
}
package site._60jong.jpashop.api.dto;

import lombok.Getter;
import site._60jong.jpashop.domain.Order;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderItemInfos {

    private List<OrderItemInfo> orderItemInfos;

    public OrderItemInfos(Order order) {
         this.orderItemInfos = order.getOrderItems()
                                    .stream()
                                    .map(OrderItemInfo::new)
                                    .collect(Collectors.toList());

    }
}

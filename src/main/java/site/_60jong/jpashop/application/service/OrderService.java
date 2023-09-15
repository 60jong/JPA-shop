package site._60jong.jpashop.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site._60jong.jpashop.domain.Member;
import site._60jong.jpashop.domain.Order;
import site._60jong.jpashop.domain.OrderItem;
import site._60jong.jpashop.domain.item.Item;
import site._60jong.jpashop.persistence.repository.ItemRepository;
import site._60jong.jpashop.persistence.repository.MemberRepository;
import site._60jong.jpashop.persistence.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long order(Long memberId, List<OrderItemInformation> informations) {
        Member member = memberRepository.find(memberId);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemInformation information : informations) {
            Item item = itemRepository.find(information.getItemId());
            orderItems.add(new OrderItem(item, information.getCount()));
        }

        // 주문 생성
        Order order = new Order(member, orderItems);

        return orderRepository.save(order);
    }

    public void cancel(Long orderId) {
        Order order = orderRepository.find(orderId);
        order.cancel();
    }
}

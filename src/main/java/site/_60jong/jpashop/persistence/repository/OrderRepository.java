package site._60jong.jpashop.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.jpashop.domain.Order;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Order find(Long orderId) {
        return em.find(Order.class, orderId);
    }
}

package site._60jong.jpashop.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.jpashop.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithFetch() {
        return em.createQuery("select distinct o from Order o " +
                        "join fetch o.member " +
                        "join fetch o.delivery " +
                        "join fetch o.orderItems oi " +
                        "join fetch oi.item", Order.class)
                .getResultList();
    }


}

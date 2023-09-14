package site._60jong.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    // Constructor
    public Member(String username, Address address) {
        this.username = username;
        this.address = address;
    }

    //== 비즈니스 로직 ==//
    public void addOrder(Order order) {
        this.orders.add(order);
    }
}

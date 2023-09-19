package site._60jong.jpashop.api.dto;

import lombok.Getter;
import site._60jong.jpashop.domain.Address;
import site._60jong.jpashop.domain.Member;

@Getter
public class MemberInfo {
    private Long id;
    private String name;
    private Address address;

    public MemberInfo(Member member) {
        this.id = member.getId();
        this.name = member.getUsername();
        this.address = member.getAddress();
    }
}

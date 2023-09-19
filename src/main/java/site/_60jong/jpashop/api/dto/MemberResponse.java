package site._60jong.jpashop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import site._60jong.jpashop.domain.Address;
import site._60jong.jpashop.domain.Member;

@Getter @Setter
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;
    private Address address;

    public static MemberResponse toDto(Member member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getAddress());
    }
}

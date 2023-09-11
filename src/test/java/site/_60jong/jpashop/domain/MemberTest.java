package site._60jong.jpashop.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    private static final String MEMBER_A_NAME = "MemberA";

    @Test
    @DisplayName("Member 생성 후 username 일치 조회")
    void member_create_test() {
        Member member = new Member(MEMBER_A_NAME, null);

        assertThat(member.getUsername()).isEqualTo(MEMBER_A_NAME);
    }
}
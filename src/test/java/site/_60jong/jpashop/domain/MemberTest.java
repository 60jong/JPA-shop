package site._60jong.jpashop.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private static final String MEMBER_A_NAME = "MemberA";

    @Test
    @DisplayName("Member 생성 후 username 일치 조회")
    void member_create_test() {
        Member member = Member.builder()
                .username(MEMBER_A_NAME)
                .build();

        assertThat(member.getUsername()).isEqualTo(MEMBER_A_NAME);
    }
}
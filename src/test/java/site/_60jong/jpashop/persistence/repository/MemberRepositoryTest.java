package site._60jong.jpashop.persistence.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site._60jong.jpashop.domain.Member;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private static final String MEMBER_A_NAME = "MemberA";

    @Test
    @DisplayName("Member 저장 후 ID 일치 확인")
    void save_member() {
        // given
        Member member = new Member(MEMBER_A_NAME, null);

        // when
        Long saveId = memberRepository.save(member);

        // then
        assertThat(saveId).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("Member 저장 후 ID로 조회해 일치 확인")
    void find_member() {
        // given
        Member member = new Member(MEMBER_A_NAME, null);

        Long saveId = memberRepository.save(member);

        // when
        Member findMember = memberRepository.find(saveId);

        // then
        assertThat(findMember).isEqualTo(member);
    }
}
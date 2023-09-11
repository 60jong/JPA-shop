package site._60jong.jpashop.application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site._60jong.jpashop.domain.Member;
import site._60jong.jpashop.persistence.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberRepository;

    @BeforeEach
    void before() {
        memberRepository = mock(MemberRepository.class);
        memberService = new MemberService(memberRepository);
    }

    @Test
    @DisplayName("멤버 저장 시 ID 리턴")
    void join_member() {
        // given
        Long saveMockMemberId = 1L;
        when(memberRepository.save(any())).thenReturn(saveMockMemberId);
        MemberService.MemberDto memberDto = getMemberDto();

        // when
        Long joinId = memberService.join(memberDto);

        // then
        assertThat(joinId).isEqualTo(saveMockMemberId);
    }

    @Test
    @DisplayName("멤버 저장 시 중복 이름이면 예외")
    void join_member_duplicate_name() {
        // given
        when(memberRepository.findByName(anyString())).thenReturn(List.of(mock(Member.class)));
        MemberService.MemberDto memberDto = getMemberDto();

        // then
        assertThatThrownBy(() -> memberService.join(memberDto)).isInstanceOf(IllegalArgumentException.class);
    }

    private static MemberService.MemberDto getMemberDto() {
        return new MemberService.MemberDto(
                "memberA", "cityA", "streetA", "zipcodeA"
        );
    }
}
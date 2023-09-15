package site._60jong.jpashop.application.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site._60jong.jpashop.domain.Address;
import site._60jong.jpashop.domain.Member;
import site._60jong.jpashop.persistence.repository.MemberRepository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.find(memberId);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> membersByName = memberRepository.findByName(member.getUsername());

        if (!membersByName.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }
}

package site._60jong.jpashop.persistence.repository;

import org.springframework.stereotype.Repository;
import site._60jong.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long memberId) {
        return em.find(Member.class, memberId);
    }
}
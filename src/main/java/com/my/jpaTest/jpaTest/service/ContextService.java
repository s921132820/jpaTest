package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.Entity.Member;
import com.my.jpaTest.jpaTest.Repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContextService {
    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository repository;

    public Member memberInsert() {
        Member member = new Member();
        member.setMemberId("안유진");
        member.setName("유진");
        // persist : DB에 저장해주세요
        em.persist(member);

        Member m = em.find(Member.class, "안유진");
        return m;
    }

    // 쓰기 지연 테스트
    public void transactionTest() {
        Member hong = new Member();
        hong.setMemberId("홍길동");
        hong.setName("길동");

        Member lee = new Member();
        lee.setMemberId("이순신");
        lee.setName("순신");

        em.persist(hong);
        em.persist(lee);
        em.flush();
    }

    public void saveTest() {
        Member jin = repository.findById("안유진").get();
        jin.setName("Jin");
        repository.save(jin);
    }

    public void dirtyCheckingTest() {
        // 영속성 컨텍스트에서 데이터 조회
        Member member = em.find(Member.class, "안유진");
        member.setName("나는안유진");
    }

    public void removeMember() {
        Member member = em.find(Member.class, "이순신");
        em.remove(member);
    }
}

package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.Entity.Member;
import com.my.jpaTest.jpaTest.Repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContextServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ContextService contextService;

    @Autowired
    MemberRepository repository;

    @Test
    @DisplayName("1차 캐시 테스트")
    void firstCash() {
        Member member = contextService.memberInsert();
        System.out.println(member);
    }

    @Test
    @DisplayName("데이서 영속성 보장 테스트")
    void persistContextTest() {
        Member a = em.find(Member.class, "안유진");
        Member b = em.find(Member.class, "안유진");
        System.out.println(a.equals(b));

        Member c = repository.findById("안유진").get();
        Member d = repository.findById("안유진").get();
        System.out.println(c.equals(d)); // true
        System.out.println(a.equals(d)); // true
    }

    @Test
    @DisplayName("Transaction Commit 테스트")
    void transactionCommitTest() {
        contextService.transactionTest();
    }

    @Test
    @DisplayName("Repo-dirtyCheckingTest 테스트")
    void saveTest() {
        contextService.saveTest();
    }

    @Test
    @DisplayName("Dirty-Checking ")
    void dirtyCheckingTest() {
        contextService.dirtyCheckingTest();
    }

    @Test
    @DisplayName("MemberRemove Test")
    void removeTest() {
        contextService.removeMember();
    }

}
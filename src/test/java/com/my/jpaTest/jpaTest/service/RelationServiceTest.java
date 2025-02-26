package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.Entity.Member;
import com.my.jpaTest.jpaTest.Entity.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RelationServiceTest {
    @Autowired
    RelationService relationService;

    @Autowired
    EntityManager em;

//    @Test
//    @DisplayName("insertMemberAndTeam")
//    void insertMemberAndTeam() {
//        relationService.insertMemberAndTeam();
//    }

//    @Test
//    @DisplayName("장원영의 팀의 이름 찾기 테스트")
//    void memberInfoSearch() {
//        Member member = em.find(Member.class, "장원영");
//        Team team = em.find(Team.class, member.getTeamId());
//        System.out.println("팀이름 : " + team.getTeamName());
//    }

    @Test
    @DisplayName("단방향 연관관계 설정 후 저장하기")
    void insertMemberAndTeamRelationTest() {
        relationService.insertMemberAndTeamRelation();
    }

    @Test
    @DisplayName("장원영의 팀의 이름 찾기 테스트")
    void memberInfoSearch() {
        Member member = em.find(Member.class, "장원영");
        System.out.println("팀이름 : " + member.getTeam().getTeamName());
    }

    @Test
    @DisplayName("insertBothDirection")
    void insertBothDirection() {
        relationService.insertBothDirection();
    }

    @Test
    @DisplayName("뉴진스 멤버이름 출력하기")
    void displayNewJeansMember() {
        Team newJeans = em.find(Team.class, "newJeans");
        List<Member> members = newJeans.getMemberList();
        for (Member member : members) {
            System.out.println("===" + member.getName());
        }
    }
}


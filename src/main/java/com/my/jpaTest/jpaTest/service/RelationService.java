package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.Entity.Member;
import com.my.jpaTest.jpaTest.Entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RelationService {
    @Autowired
    EntityManager em;

//    public void insertMemberAndTeam() {
//        Team ive = new Team();
//        ive.setTeamId("ive");
//        ive.setTeamName("아이브");
//        em.persist(ive);
//
//        Member jang = new Member();
//        jang.setMemberId("장원영");
//        jang.setName("원영");
//        jang.setTeamId("ive");
//        em.persist(jang);
//    }

    public void insertMemberAndTeamRelation() {
        // 단방향 매핑 후 저장하기
        // 팀 생성 후 저장
        Team ive = new Team();
        ive.setTeamId("ive");
        ive.setTeamName("아이브");
        em.persist(ive);

        // 멤버 생성 후 저장
        Member jang = new Member();
        jang.setMemberId("장원영");
        jang.setName("원영");
        jang.setTeam(ive);
        em.persist(jang);
    }

    public void insertBothDirection() {
        // 뉴진스 팀 생성
        Team newJeans = new Team();
        newJeans.setTeamId("newJeans");
        newJeans.setTeamName("뉴진스");
        em.persist(newJeans);

        // 하니와 다니엘을 생성
        Member hani = new Member();
        hani.setMemberId("하니");
        hani.setName("Hani");
        hani.setTeam(newJeans);
        // 팀 리스트에 추가
        newJeans.getMemberList().add(hani);
        em.persist(hani);

        Member daniel = new Member();
        daniel.setMemberId("다니엘");
        daniel.setName("Daniel");
        daniel.setTeam(newJeans);
        newJeans.getMemberList().add(daniel);
        em.persist(daniel);
    }
}

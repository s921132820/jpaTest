package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.examEntity.Entertainment;
import com.my.jpaTest.jpaTest.examEntity.GirlGroup;
import com.my.jpaTest.jpaTest.examEntity.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExamService {
    @Autowired
    EntityManager em;

    // 지수가 가진 걸그룹 이름과 엔터테이먼트 이름 출력하기
    public void searchJisuInfo() {
        IdolMember jisu = em.find(IdolMember.class, "지수");
        String group = jisu.getGirlGroup().getName();
        String company = jisu.getGirlGroup().getEntertainment().getName();
        System.out.println("그룹 이름 : " + group);
        System.out.println("회사 이름 : " + company);
    }

    // 블랙핑크 멤버 리스트 출력하기
    public void searchGroupMember(String groupName) {
        GirlGroup girlGroup = em.find(GirlGroup.class, "blackPink");
        for (IdolMember member : girlGroup.getIdolMemberList()) {
            System.out.println("멤버 아이디 : " + member.getId());
            System.out.println("멤버 이름 : " + member.getName());
        }
    }

    // ive 멤버 중에 "가을 추가"
    public void addMember() {
        // 아이브 불러와서 추가
        GirlGroup ive = em.find(GirlGroup.class, "ive");

        // 가을 객체 만들고
        IdolMember gaul = new IdolMember();
        gaul.setId("가을");
        gaul.setName("가을이야");
        gaul.setGirlGroup(ive);

        ive.getIdolMemberList().add(gaul);
    }



    public void initData() {
        IdolMember member_1 = new IdolMember();
        IdolMember member_2 = new IdolMember();
        IdolMember member_3 = new IdolMember();
        IdolMember member_4 = new IdolMember();

        member_1.setId("안유진");
        member_1.setName("유진");
        member_2.setId("장원영");
        member_2.setName("원영");
        member_3.setId("제니");
        member_3.setName("재니");
        member_4.setId("지수");
        member_4.setName("지수다");

        GirlGroup girlGroup_1 = new GirlGroup();
        GirlGroup girlGroup_2 = new GirlGroup();

        girlGroup_1.setId("ive");
        girlGroup_1.setName("아이브");
        girlGroup_2.setId("blackPink");
        girlGroup_2.setName("블핑");

        Entertainment entertainment_1 = new Entertainment();
        Entertainment entertainment_2 = new Entertainment();

        entertainment_1.setId("startship");
        entertainment_1.setName("스타쉽");
        entertainment_2.setId("YG");
        entertainment_2.setName("와이지");

        // 🔥 관계 설정 (FK 설정)
        member_1.setGirlGroup(girlGroup_1);  // 유진 → 아이브
        member_2.setGirlGroup(girlGroup_1);  // 원영 → 아이브
        member_3.setGirlGroup(girlGroup_2);  // 재니 → 블핑
        member_4.setGirlGroup(girlGroup_2);  // 지수 → 블핑

        girlGroup_1.setEntertainment(entertainment_1);  // 아이브 → 스타쉽
        girlGroup_2.setEntertainment(entertainment_2);  // 블핑 → YG

        // 🔥 @OneToMany 리스트에 추가
        girlGroup_1.getIdolMemberList().add(member_1);
        girlGroup_1.getIdolMemberList().add(member_2);
        girlGroup_2.getIdolMemberList().add(member_3);
        girlGroup_2.getIdolMemberList().add(member_4);

        entertainment_1.getGirlGroupList().add(girlGroup_1);
        entertainment_2.getGirlGroupList().add(girlGroup_2);

        // 🔥 저장
        em.persist(entertainment_1);
        em.persist(entertainment_2);
        em.persist(girlGroup_1);
        em.persist(girlGroup_2);
        em.persist(member_1);
        em.persist(member_2);
        em.persist(member_3);
        em.persist(member_4);
    }
}
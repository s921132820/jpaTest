package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.examEntity.GirlGroup;
import com.my.jpaTest.jpaTest.examEntity.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QueryService {
    @Autowired
    EntityManager em;

    // 동적 쿼리 생성(ive에서 장원영 검색)
    public List<IdolMember> dynimicQuery() {
        String sql = "SELECT idol FROM IdolMember idol " +
                "WHERE idol.name=:nickname";
        TypedQuery<IdolMember> query = em.createQuery(sql, IdolMember.class)
                .setParameter("nickname", "원영");
        List<IdolMember> memberList = query.getResultList();
        return memberList;
    }

    // 동적쿼리(걸그룹 전체 검색)
    public List<GirlGroup> findAllGirlGroup() {
        String sql = "SELECT g FROM GirlGroup AS g ";
        Query query = em.createQuery(sql);
        List<GirlGroup> girlGroupList = query.getResultList();
        return girlGroupList;
    }

    // GirlGroup에서 멤버 name만 검색해서 찾아오기
    public List<String> findMemberName() {
        String sql = "SELECT m.name FROM IdolMember AS m ";
        TypedQuery<String> query = em.createQuery(sql, String.class);
        List<String> nameList = query.getResultList();
        return nameList;
    }
}

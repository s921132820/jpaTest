package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.dto.MemberInfo;
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

    // 멤버 중에 blackPink 소속의 이름만 출력
    public List<String> findPinkMembers() {
        String sql = "SELECT m.name FROM IdolMember AS m " +
                "WHERE m.girlGroup.name=:groupName";
        TypedQuery<String> query = em.createQuery(sql, String.class)
                .setParameter("groupName", "블랙핑크");
        return query.getResultList();
    }

    // Ive 멤버의 인원수 구하는 쿼리
    public Long memberCount() {
        String sql = "SELECT COUNT(m) FROM IdolMember m " +
                "WHERE m.girlGroup.name=:groupName";
        Query query = em.createQuery(sql);
        query.setParameter("groupName", "아이브");
        Long result = (Long) query.getSingleResult();
        return result;
    }

    // DTO(MemberInfo)로 받기
    // name, groupName, enterName
    public List<MemberInfo> getMemberInfoList() {
        String sql = "SELECT NEW " +
                "com.my.jpaTest.jpaTest.dto.MemberInfo(" +
                "m.name, m.girlGroup.name, m.girlGroup.entertainment.name)" +
                " FROM IdolMember AS m";
        TypedQuery<MemberInfo> query = em.createQuery(sql, MemberInfo.class);
        return query.getResultList();
    }
}

package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.examEntity.GirlGroup;
import com.my.jpaTest.jpaTest.examEntity.IdolMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryServiceTest {
    @Autowired
    QueryService queryService;

    @Test
    @DisplayName("dynimicQuery:원영찾기")
    void dynimicQuery() {
        List<IdolMember> memberList = queryService.dynimicQuery();
        memberList.stream()
                .forEach(x -> System.out.println("name : " + x.getName()));
    }

    @Test
    @DisplayName("findAllGirlGroup")
    void findAllGirlGroup() {
        List<GirlGroup> girlGroupList = queryService.findAllGirlGroup();
        for (GirlGroup group : girlGroupList) {
            System.out.println("Group Name : " + group.getName());
            System.out.println("====================");
            for (IdolMember member : group.getIdolMemberList()) {
                System.out.println("Member : " + member.getId());
            }
        }
    }

    @Test
    @DisplayName("findMemberName")
    void findMemberName() {
        queryService.findMemberName().stream().forEach(x-> System.out.println("name : " + x));
    }

    @Test
    @DisplayName("findPinkMembers")
    void findPinkMembers() {
        queryService.findPinkMembers().stream().forEach(x-> System.out.println("name : " + x));
    }

    @Test
    @DisplayName("memberCount")
    void memberCount() {
        System.out.println("아이브 인원 수 : " + queryService.memberCount());
    }

    @Test
    @DisplayName("getMemberInfoList")
    void getMemberInfoList() {
        queryService.getMemberInfoList()
                .stream().forEach(x -> System.out.println(x));

    }
}
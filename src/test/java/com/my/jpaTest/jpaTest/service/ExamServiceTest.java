package com.my.jpaTest.jpaTest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamServiceTest {
    @Autowired
    ExamService examService;

    @Test
    @DisplayName("데이터 삽입 테스트")
    void initDataTest() {
        examService.initData();
    }

    @Test
    @DisplayName("지수가 속한 걸그룹이름과 회사")
    void searchJisuInfo() {
        examService.searchJisuInfo();
    }

    @Test
    @DisplayName("해당 그룹에 있는 멤버 목록 출력")
    void searchGroupMember() {
        String findGroup = "blackPick";
        examService.searchGroupMember(findGroup);
    }

    @Test
    @DisplayName("아이브에 가을이 추가하기")
    void addMember() {
        examService.addMember();
    }
}
package com.my.jpaTest.jpaTest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CascadeServiceTest {
    @Autowired
    CascadeService cascadeService;

    @Test
    @DisplayName("Cascade 설정 이전에 저장 테스트")
    void insertData() {
        cascadeService.insertData();
    }

    @Test
    @DisplayName("Cascade 설정 이후에 저장 테스트")
    void cascadeTest() {
        cascadeService.cascadeInsert();
    }

    @Test
    @DisplayName("Cascade 설정 이후에 삭제 테스트")
    void cascadeRemoveTest() {
        cascadeService.cascadeRemoveTest();
    }
}
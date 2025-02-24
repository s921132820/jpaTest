package com.my.jpaTest.jpaTest.Repository;

import com.my.jpaTest.jpaTest.Entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class UsersTestRepositoryTest {
    @Autowired
    UsersTestRepository usersTestRepository;

    @Test
    @DisplayName("1.여성의 이름 중 \"w\"또는 \"m\"을 포함하는 자료")
    //문제 1. 를 검색하시오.
    void findByNameContaining() {
        usersTestRepository.findByNameContaining("w")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("1.테스트 - 카운트")
    void countByNameContaining() {
        Long result = usersTestRepository.countByNameContaining("w");
        System.out.println(result);
    }

    @Test
    @DisplayName("2.이메일에 net을 포함하는 데이터 건수")
    void countByEmailContaining() {
        Long result = usersTestRepository.countByEmailContaining("net");
        System.out.println(result);
    }

    @Test
    @DisplayName("3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 \"J\"인 자료를 출력")
    void test3() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusMonths(1);
        List<Users> resultUser = usersTestRepository
                .findByUpdatedAtBetweenAndNameStartingWith(start, end, "j");
        System.out.println(resultUser);
    }

    @Test
    @DisplayName("4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력")
    void test4() {
        List<Users> resultUser = usersTestRepository.findTop10ByOrderByCreatedAtDesc();

        resultUser.forEach(x -> System.out.println(
                "Id : " + x.getId() +
                "Name : " + x.getName() +
                "Gender : " + x.getGender() +
                "Created_at : " + x.getCreatedAt()
        ));
    }
}

//문제 5. "Red"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력하시오.
//(예, apenley2@tripod.com  → apenley2)
//
//문제 6. 갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.
//
//문제 7. 이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력하시오.
//
//문제 8. 좋아하는 색상(Pink)별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력하시오.
//
//문제 9. 전체 자료를 가장 최근 입력한 자료 순으로 정렬 및 페이징 처리하고 한 페이지당 10건씩 출력하되,
//그 중 1번째 페이지를 출력하시오.
//
//문제10. 남성 자료를 ID의 내림차순으로 정렬한 후 한페이당 3건을 출력하되 그 중 2번째 페이지 자료를  출력하시오.
//
//문제11. 지난달의 모든 자료를 검색하여 출력하시오.
package com.my.jpaTest.jpaTest.Repository;

import com.my.jpaTest.jpaTest.Entity.Users;
import com.my.jpaTest.jpaTest.constant.Gender;
import com.my.jpaTest.jpaTest.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class UsersTestRepositoryTest {
    @Autowired
    UsersTestRepository usersTestRepository;

    @Test
    @DisplayName("1.여성의 이름 중 \"w\"또는 \"m\"을 포함하는 자료")
    //문제 1. 를 검색하시오.
    void test1() {
        usersTestRepository.findByNameContaining("w")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("1_1")
    void test1_1() {
        usersTestRepository.findByGenderAndNameLikeOrNameLike(
                Gender.Female,"%w%", "%m%")
                .forEach(x -> System.out.println());
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
    @DisplayName("2_1")
    void test2_1() {
        System.out.println("결과건수" + usersTestRepository.findByEmailLike("%net")
                .stream().count());
    }

    // 문제 3
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
    @DisplayName("3_1")
    void test3_1() {
        LocalDate baseDate = LocalDate.now().minusMonths(1L);
        LocalDateTime start = baseDate.atTime(0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        usersTestRepository.findByUpdatedAtBetweenAndNameLike(
                start, end, "j%"
        ).forEach(x -> System.out.println(x));
    }

    // 문제 4
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

    @Test
    @DisplayName("4_1")
    // UserDto를 따로 생성해서 출력
    void test4_1() {
        usersTestRepository.findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(x -> UserDto.fromEntity(x))
                .toList()
                .forEach(x -> System.out.println(x));
    }

    // 문제 5
    @Test
    @DisplayName("5. \"Red\"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력")
     //(예, apenley2@tripod.com  → apenley2)
    void test5() {
        List<Users> usersList = usersTestRepository.findByLikeColorAndGender("Red", Gender.Male);
        for (Users userData : usersList) {
            String userEmail = userData.getEmail()
                    .substring(0, userData.getEmail().indexOf("@"));
            System.out.println(userEmail);
        }
    }

    // 문제 6
    @Test
    @DisplayName("6. 갱신일이 생성일 이전인 잘못된 데이터를 출력")
    void test6() {
        usersTestRepository
                .findAll()
                .stream()
                .filter(x -> x.getUpdatedAt().isBefore(x.getCreatedAt()))
                .forEach(x -> System.out.println(x));
    }

    //문제 7
    @Test
    @DisplayName("7. 이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력")
    void test7() {
    usersTestRepository.findByGenderAndEmailContainsOrderByCreatedAtDesc
            (
            Gender.Female, "edu")
            .forEach(x -> System.out.println(x));
    }

    //문제 8-1
    @Test
    @DisplayName("8. 좋아하는 색상(Pink)별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력")
    void test8() {
        usersTestRepository
                .findByOrderByLikeColorAscNameDesc()
                .forEach(x -> System.out.println(x));
    }

    //문제 8-2
    @Test
    @DisplayName("8-2. 좋아하는 색상(Pink)별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력")
    void test8_2() {
        usersTestRepository
                .findAll(
                        Sort.by(
                        Sort.Order.asc("likeColor"),
                        Sort.Order.desc("name")
                    )).forEach(x -> System.out.println(x));
    }

    // 페이징 테스트
    @Test
    @DisplayName("Paging Test")
    void pagingTest() {
        System.out.println("페이지 = 0, 페이지당 리스트 수 : 10개");
        usersTestRepository.findAll(
                PageRequest.of(49, 5, Sort.by(Sort.Order.asc("name")))
        ).forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByIdGreaterThanOrderByDesc")
    void findByIdGreaterThanOrderByDesc() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Users> result = usersTestRepository.findByIdGreaterThanOrderByIdDesc(100L, pageable);
        result.getContent().forEach(x -> System.out.println(x));

        // 총페이지수
        System.out.println("총 페이지 수 : " + result.getTotalPages());

        // 전체 데이터 수
        System.out.println("전체 데이터 수 : " + result.getTotalElements());

        // 현재 페이지 번호
        System.out.println("현재 페이지 번호 : " + result.getNumber());

        // 페이지 당 데이터 수
        System.out.println("페이지 당 데이터 수 : " + result.getSize());

        // 다음 페이지 존재 여부
        System.out.println("다음 페이지? : " + result.hasNext());

        // 이전 페이지 존재 여부
        System.out.println("이전 페이지? : " + result.hasPrevious());

        // 첫번째 페이지에 있는지 여부
        System.out.println("이전 페이지? : " + result.isFirst());
    }

    // 문제9
    @Test
    @DisplayName("9. 전체 자료를 가장 최근 입력한 자료 순으로 정렬 및 페이징 처리하고 한 페이지당 10건씩 출력하되,\n" +
            "//그 중 1번째 페이지를 출력")
    void test9() {
        usersTestRepository.findAll(
            PageRequest.of(
                    0, 10,
                    Sort.by(Sort.Order.desc("createdAt"))
            ))
            .forEach(x -> System.out.println(x));
    }

    // 문제 10
    @Test
    @DisplayName("10. 남성 자료를 ID의 내림차순으로 정렬한 후 한페이당 3건을 출력하되 그 중 2번째 페이지 자료를  출력")
    void test10() {
        Pageable pageable = PageRequest.of(1, 3);
        Page<Users> result = usersTestRepository.findByGenderOrderByIdDesc(Gender.Male, pageable);
        result.getContent().forEach(x -> System.out.println(x));
    }
}


//문제11. 지난달의 모든 자료를 검색하여 출력하시오.
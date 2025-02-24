package com.my.jpaTest.jpaTest.Repository;

import com.my.jpaTest.jpaTest.Entity.Users;
import com.my.jpaTest.jpaTest.constant.Gender;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 통합테스트 : 톰캣을 띄우고 전체를 실행하도록 만들고 테스트
@SpringBootTest

class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    void totalCountTest() {
        // Given
        // When
        Long rowCount = usersRepository.findAll().stream().count();
        // Then
        assertThat(rowCount).isEqualTo(500);
    }

    @Test
    @Transactional
    @DisplayName("사용자 입력 테스트")
    void userInputTest() {
        Users jang = Users.builder()
                .name("장원영")
                .email("a@b.c")
                .likeColor("black")
                .gender(Gender.Female)
                .createdAt(LocalDateTime.now())
                .build();

        usersRepository.save(jang);

        System.out.println("======" + usersRepository.findAll().stream().count());
    }

    @Test
    @DisplayName("삭제 테스트(1번 삭제)")
    void userDeleteTest() {
        //Given

        //When
        usersRepository.deleteById(1L);

        //Then
        Boolean exist = usersRepository.existsById(1L);
        assertThat(exist).isEqualTo(false);
    }

    @Test
    @DisplayName("업데이트테스트 - 1번 이름 바꾸기")
    void userUpdateTest() {
        // 1번이 존재하면 1번이 좋아하는 색상을 빨강으로 수정
        //When
        if(usersRepository.existsById(1L)) {
            Optional<Users> oldUser = usersRepository.findById(1L);
            Users newUser = oldUser.get();
            newUser.setLikeColor("빨강");

            //수정처리
            usersRepository.save(newUser);
        }
        // Then
        Optional<Users> findUser = usersRepository.findById(1L);
        assertThat(findUser.get().getLikeColor()).isEqualTo("빨강");
    }

    @Test
    @DisplayName("findByName 테스트")
    void findByNameTest() {
        String name = "dvearncombe0";
        List<Users> result = usersRepository.findByName(name);
        result.forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findTop3Bylikecolor")
    void findTop3Bylikecolor() {
        usersRepository.findTop3ByLikeColor("Pink").forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("findByGenderAndLikeColor")
    void findByGenderAndLikeColor() {
        usersRepository.findByGenderAndLikeColor(
                Gender.Male, "Yellow"
        ).forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByCreatedAtBeetween")
    void findByCreatedAtBeetween() {
        LocalDate baseDate = LocalDate.now().minusDays(1);
        // 7일전 시작 날짜와 시각
        LocalDateTime startTime = baseDate.atTime(0,0,0).minusDays(6);
        // 어제 검색 종료 날짜 시각
        LocalDateTime endTime = baseDate.atTime(23,59,59);

        System.out.println("===== startTime :" + startTime);
        System.out.println("===== endTime :" + endTime);

        usersRepository.findByCreatedAtBetween(startTime, endTime)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByCreatedAtBeetween")
    void findByCreatedAtBeetween_2() {
        // 지난달 자료 검색
        LocalDate baseDate = LocalDate.now().minusMonths(1);
        // 지난 달 1일의 날짜와 시각
        LocalDateTime startTime = baseDate.withDayOfMonth(1).atTime(0, 0, 0);
        // 지난 달 마지막 날짜
        LocalDateTime endTime = baseDate.withDayOfMonth(
                baseDate.lengthOfMonth()
        ).atTime(23,59,59);
        System.out.println("===== startTime :" + startTime);
        System.out.println("===== endTime :" + endTime);

//        usersRepository.findByCreatedAtBetween(startTime, endTime)
//                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByLikeColorIn")
    void findByLikeColorIn() {
        // 좋아하는 색상이 "Red", "Orange" 인 자료 검색
        List<String> findColorList =  new ArrayList<>(
                Arrays.asList("Red", "Orange")
        );

        List<Users> users =  usersRepository.findByLikeColorIn(findColorList);
        for(Users x : users) {
            System.out.println(x);
        }
    }

    @Test
    @DisplayName("findByNameStartingWith")
    void findByNameStartingWith() {
        // 이름이 "d"로 시작
        List<Users> nameD = usersRepository.findByNameStartingWith("d");
        for(Users x : nameD) {
            System.out.println(x);
        }
    }

    @Test
    @DisplayName("findByNameEndingWith")
    void findByNameEndingWith() {
        // 이름이 s로 끝나는 사람
        List<Users> nameS = usersRepository.findByNameEndingWith("s");
        for(Users s : nameS) {
            System.out.println(s);
        }
    }

    @Test
    @DisplayName("findByNameContains")
    void findByNameContains() {
        // 이름 중에 k가 포함되어 있는 사람
        List<Users> nameK = usersRepository.findByNameContains("k");
        for(Users k : nameK) {
            System.out.println(k);
        }
    }

    @Test
    @DisplayName("findByNameLike")
    void findByNameLike() {
        // 이름 중에 k가 포함되어 있는 사람
        usersRepository.findByNameLike("%k%")
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("findByIdBetweenOrderByNameDesc")
    void findByIdBetweenOrderByNameDesc() {
        usersRepository.findByIdBetweenOrderByNameDesc(1L, 10L)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findTop10ByLikeColorOrderByGenderAscCreatedAtDesc")
    void findTop10ByLikeColorOrderByGenderAscCreatedAtDesc() {
        usersRepository.findTop10ByLikeColorOrderByGenderAscCreatedAtDesc("Orange")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findTop20LikeColor")
    void findTop20ByLikeColor() {
        usersRepository.findTop20ByLikeColor("Red", getSort())
                .forEach(x -> System.out.println(x));
    }

    // Order 객체 전달을 변도로 처리
    private Sort getSort() {
        return Sort.by(Sort.Order.asc("gender"), Sort.Order.desc("createdAt"));
    }
}

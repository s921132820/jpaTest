package com.my.jpaTest.jpaTest.Repository;

import com.my.jpaTest.jpaTest.Entity.Users;
import com.my.jpaTest.jpaTest.constant.Gender;
import org.apache.catalina.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // 이름으로 검색하는 기능
    List<Users> findByName(String name);

    // 색상 값을 받아서 그 중 처음으로 발견되는 3개의 데이터를 출력
    List<Users> findTop3ByLikeColor(String color);

    // 남자이면서 색상이 Yellow
    List<Users> findByGenderAndLikeColor(Gender gender, String color);

    // 범위 검색
    // After, Before (날짜와, 시간을 검색하는데 주로 쓰임)
    // "=" 포함하지 않음
    // 관계연산자 : GreaterThenEquals, LessThen...
    // between 연산자
    // 최근 7일 이내 자료를 읽어오고 싶을 때(오늘 빼고)
    List<Users> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // Null / IsNotNull

    // In 구문
    List<Users> findByLikeColorIn(List<String> colors);

    // 문자열 검색 관련 구문
    // Like(%검색어%), Contains(검색어), StartingWith, EndingWith
    List<Users> findByNameStartingWith(String name);

    List<Users> findByNameEndingWith(String Name);

    List<Users> findByNameContains(String name);

    List<Users> findByNameLike(String name);


    // Sorting 하고 순서대로 ... Asc(Ascending) Desc(Descending)
    List<Users> findByIdBetweenOrderByNameDesc(Long Start, Long End);

    // Orange 색상 중 상위 10개 검색해서 gender의 오름차순, Create_at의 내림차순
    List<Users> findTop10ByLikeColorOrderByGenderAscCreatedAtDesc(String color);

    List<Users> findTop20ByLikeColor(String Color, Sort sort);
}


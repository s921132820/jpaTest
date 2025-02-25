package com.my.jpaTest.jpaTest.Repository;

import com.my.jpaTest.jpaTest.Entity.Users;
import com.my.jpaTest.jpaTest.constant.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface UsersTestRepository extends JpaRepository<Users, Long> {
    // 1. 여성의 이름 중 "w"또는 "m"을 포함하는 자료를 검색하시오.
    List<Users> findByGenderAndNameLikeOrNameLike(Gender gender, String str1, String str2);
    List<Users> findByNameContaining(String name);

        // 리스트 형태가 아니라 count 알고 싶을 때
    Long countByNameContaining(String name);

    // 2. 이메일에 net을 포함하는 데이터 건수를 출력하시오.
    Long countByEmailContaining(String email);
    List<Users> findByEmailLike(String email);

    // 3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 "J"인 자료
    List<Users> findByUpdatedAtBetweenAndNameStartingWith(LocalDateTime start, LocalDateTime end, String name);
    List<Users> findByUpdatedAtBetweenAndNameLike(LocalDateTime start, LocalDateTime end, String first);

    // 4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력
    List<Users> findTop10ByOrderByCreatedAtDesc();

    // 5. "Red"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력
    //(예, apenley2@tripod.com  → apenley2)
    List<Users> findByLikeColorAndGender(String color, Gender gender);

    // 6. 갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.
    //    List<Users> findByUpdatedAtLessthenCreatedAt();

    // 7. 이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력하시오.
    List<Users> findByGenderAndEmailContainsOrderByCreatedAtDesc(Gender gender, String contains);

    // 8. 좋아하는 색상(Pink)별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력하시오.
    List<Users> findByOrderByLikeColorAscNameDesc();

    // 주어진 아이디보다 큰 데이터를 내림차순으로 검색 후 페이징 처리
    Page<Users> findByIdGreaterThanOrderByIdDesc(Long Id, Pageable pageable);

    // 9. 전체 자료를 가장 최근 입력한 자료 순으로 정렬 및 페이징 처리하고 한 페이지당 10건씩 출력하되,
    //그 중 1번째 페이지를 출력하시오.


    // 10. 남성 자료를 ID의 내림차순으로 정렬한 후 한페이당 3건을 출력하되 그 중 2번째 페이지 자료를  출력하시오.
    Page<Users> findByGenderOrderByIdDesc(Gender gender, Pageable pageable);
    //11. 지난달의 모든 자료를 검색하여 출력하시오.
}

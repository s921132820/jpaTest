package com.my.jpaTest.jpaTest.Repository;

import com.my.jpaTest.jpaTest.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}

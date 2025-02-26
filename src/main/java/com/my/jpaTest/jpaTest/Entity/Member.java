package com.my.jpaTest.jpaTest.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @Column(nullable = false, unique = true)
    private String memberId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}

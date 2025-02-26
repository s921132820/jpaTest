package com.my.jpaTest.jpaTest.examEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class GirlGroup {
    @Id
    @Column(name = "g_id")
    private String id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "e_id")
    Entertainment entertainment;

    @OneToMany(mappedBy = "girlGroup",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    List<IdolMember> idolMemberList = new ArrayList<>();
}

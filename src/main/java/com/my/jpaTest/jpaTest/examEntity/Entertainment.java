package com.my.jpaTest.jpaTest.examEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Entertainment {
    @Id
    @Column(name = "e_id")
    private String id;
    private String name;

    @OneToMany(mappedBy = "entertainment",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    List<GirlGroup> girlGroupList = new ArrayList<>();
}

package com.my.jpaTest.jpaTest.examEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class IdolMember {
    @Id
    @Column(name = "i_id")
    private String id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "g_id")
    private GirlGroup girlGroup;
}

package com.my.jpaTest.jpaTest.service;

import com.my.jpaTest.jpaTest.Entity.Child;
import com.my.jpaTest.jpaTest.Entity.Parent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CascadeService {
    @Autowired
    EntityManager em;

    public void cascadeInsert() {
        Child child_1 = new Child();
        Child child_2 = new Child();

        Parent parent = new Parent();
        child_1.setParent(parent);
        child_2.setParent(parent);
        parent.getChiledList().add(child_1);
        parent.getChiledList().add(child_2);

        // 부모만 저장
        em.persist(parent);
    }

    public void insertData() {
         // 부모 객체 1개에 자식 객체 2개 담아서 저장
        // 부모 저장
        Parent parent = new Parent();
        em.persist(parent);

        // 자식1(Child_1) 저장
        Child child_1 = new Child();
        child_1.setParent(parent);
        parent.getChiledList().add(child_1);
        em.persist(child_1);

        // 자식2(Child_2) 저장
        Child child_2 = new Child();
        child_2.setParent(parent);
        parent.getChiledList().add(child_2);
        em.persist(child_2);
    }

    public void cascadeRemoveTest() {
        // 1번 부모 삭제
        Parent parent = em.find(Parent.class, 1L);
        em.remove(parent);
    }
}

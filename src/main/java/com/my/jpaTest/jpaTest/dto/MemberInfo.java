package com.my.jpaTest.jpaTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInfo {
    private String name;
    private String groupName;
    private String enterName;
}

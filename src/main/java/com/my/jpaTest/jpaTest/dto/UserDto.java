package com.my.jpaTest.jpaTest.dto;

import com.my.jpaTest.jpaTest.Entity.Users;
import com.my.jpaTest.jpaTest.constant.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDateTime createdAt;

    public static UserDto fromEntity(Users users) {
        return new UserDto(
                users.getId(),
                users.getName(),
                users.getGender(),
                users.getCreatedAt()
        );
    }
}

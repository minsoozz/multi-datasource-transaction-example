package com.example.batch.active.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ActiveMemberDto {

    private Long id;
    private String name;
    private int age;
    private String phoneNumber;

    public ActiveMemberDto(Long id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public static ActiveMemberDto toDto(Long id, String name, int age, String phoneNumber) {
        return new ActiveMemberDto(id, name, age, phoneNumber);
    }
}

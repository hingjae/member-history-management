package com.honey.member_history_management.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreateForm {
    private String id;

    private String password;

    private String name;

    private int age;

    private String phoneNumber;

    @Builder
    public MemberCreateForm(String id, String password, String name, int age, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public Member toEntity(String encodedPassword, Role role) {
        return Member.builder()
                .id(id)
                .password(encodedPassword)
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();

    }
}

package com.honey.member_history_management.member.dto;

import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.Role;
import com.honey.member_history_management.team.domain.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreateForm {
    private String id;

    private String password;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String teamId;

    @Builder
    public MemberCreateForm(String id, String password, String name, Integer age, String phoneNumber, String teamId) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.teamId = teamId;
    }

    public Member toEntity(String encodedPassword, Role role, Team team) {
        return Member.builder()
                .id(id)
                .password(encodedPassword)
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .role(role)
                .team(team)
                .build();

    }
}

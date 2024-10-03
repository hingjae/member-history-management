package com.honey.member_history_management.member.dto;

import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.Role;
import com.honey.member_history_management.team.domain.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberForm {
    private final String id;

    private final String password;

    private final String name;

    private final Integer age;

    private final String phoneNumber;

    private final String teamId;

    @Builder
    public MemberForm(String id, String password, String name, Integer age, String phoneNumber, String teamId) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.teamId = teamId;
    }

    public static MemberForm from(Member member) {
        String teamId = member.getTeam() != null ? member.getTeam().getId() : null;

        return MemberForm.builder()
                .id(member.getId())
                .password(member.getPassword())
                .name(member.getName())
                .age(member.getAge())
                .phoneNumber(member.getPhoneNumber())
                .teamId(teamId)
                .build();
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

package com.honey.member_history_management.envers.web.dto;

import com.honey.member_history_management.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final String id;

    private final String teamName;

    private final String name;

    private final Integer age;

    private final String phoneNumber;

    @Builder
    public MemberResponse(String id, String teamName, String name, Integer age, String phoneNumber) {
        this.id = id;
        this.teamName = teamName;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .teamName(member.getTeam() != null ? member.getTeam().getName() : null) // n + 1
                .name(member.getName())
                .age(member.getAge())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}

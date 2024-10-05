package com.honey.member_history_management.envers.web.dto;

import com.honey.member_history_management.envers.repository.dto.RevisionWithMetadata;
import com.honey.member_history_management.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberRevisionListResponse {
    private final List<MemberRevisionResponse> items;

    @Builder
    public MemberRevisionListResponse(List<MemberRevisionResponse> items) {
        this.items = items;
    }

    public static MemberRevisionListResponse from(List<RevisionWithMetadata<Member>> items) {
        List<MemberRevisionResponse> responses = items.stream()
                .map(MemberRevisionResponse::from)
                .toList();

        return MemberRevisionListResponse.builder()
                .items(responses)
                .build();
    }

}

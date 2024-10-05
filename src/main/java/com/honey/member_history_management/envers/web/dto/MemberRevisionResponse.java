package com.honey.member_history_management.envers.web.dto;

import com.honey.member_history_management.envers.repository.dto.RevisionWithMetadata;
import com.honey.member_history_management.envers.revision.Revinfo;
import com.honey.member_history_management.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.envers.RevisionType;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MemberRevisionResponse {

    private final MemberResponse member;

    private final RevinfoResponse revinfo;

    private final String revisionType;

    private final Set<RevisionEvent> revisionEvents;

    public static final Map<String, RevisionEvent> MODIFY_EVENT_MAP = Arrays.stream(RevisionEvent.values())
            .collect(Collectors.toMap(RevisionEvent::getField, event -> event));

    @Builder
    public MemberRevisionResponse(Member member, Revinfo revinfo, RevisionType revisionType, Set<RevisionEvent> revisionEvents) {
        this.member = MemberResponse.from(member);
        this.revinfo = RevinfoResponse.from(revinfo);
        this.revisionType = revisionType.name();
        this.revisionEvents = revisionEvents;
    }

    public static MemberRevisionResponse from(RevisionWithMetadata<Member> revisionWithMetadata) {
        return MemberRevisionResponse.builder()
                .member(revisionWithMetadata.getEntity())
                .revinfo(revisionWithMetadata.getRevinfo())
                .revisionType(revisionWithMetadata.getRevisionType())
                .revisionEvents(
                        modifyEvents(revisionWithMetadata.getRevisionType(), revisionWithMetadata.getModifiedFields())
                )
                .build();
    }

    private static Set<RevisionEvent> modifyEvents(RevisionType revisionType, Set<String> modifiedFields) {
        return switch (revisionType) {
            case RevisionType.ADD -> Set.of(RevisionEvent.ADD);
            case RevisionType.DEL -> Set.of(RevisionEvent.DEL);
            case RevisionType.MOD -> modifiedFields.stream()
                    .map(MODIFY_EVENT_MAP::get)
                    .collect(Collectors.toSet());
        };
    }
}

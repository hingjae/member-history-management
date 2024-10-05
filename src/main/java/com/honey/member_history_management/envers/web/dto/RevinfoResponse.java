package com.honey.member_history_management.envers.web.dto;

import com.honey.member_history_management.envers.revision.Revinfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RevinfoResponse {
    private final Long rev;

    private final LocalDateTime updatedAt;

    private final String updatedBy;

    @Builder
    public RevinfoResponse(Long rev, LocalDateTime updatedAt, String updatedBy) {
        this.rev = rev;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public static RevinfoResponse from(Revinfo revinfo) {
        return RevinfoResponse.builder()
                .rev(revinfo.getRev())
                .updatedAt(revinfo.getUpdatedAt())
                .updatedBy(revinfo.getUpdatedBy())
                .build();
    }
}

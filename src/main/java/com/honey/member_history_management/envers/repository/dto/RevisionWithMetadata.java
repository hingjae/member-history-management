package com.honey.member_history_management.envers.repository.dto;

import com.honey.member_history_management.envers.revision.Revinfo;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.envers.RevisionType;

import java.util.Set;

@Getter
public class RevisionWithMetadata<T> {
    private final T entity;

    private final Revinfo revinfo;

    private final RevisionType revisionType;

    private final Set<String> modifiedFields;

    @Builder
    public RevisionWithMetadata(T entity, Revinfo revinfo, RevisionType revisionType, Set<String> modifiedFields) {
        this.entity = entity;
        this.revinfo = revinfo;
        this.revisionType = revisionType;
        this.modifiedFields = modifiedFields;
    }

    public static <T> RevisionWithMetadata<T> from(Object[] objects) {
        return RevisionWithMetadata.<T>builder()
                .entity((T) objects[0])
                .revinfo((Revinfo) objects[1])
                .revisionType((RevisionType) objects[2])
                .modifiedFields((Set<String>) objects[3])
                .build();
    }
}

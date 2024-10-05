package com.honey.member_history_management.envers.repository;

import com.honey.member_history_management.envers.repository.dto.RevisionWithMetadata;
import com.honey.member_history_management.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@RequiredArgsConstructor
public class MemberRevisionRepository {
    private final AuditReader auditReader;

    public List<RevisionWithMetadata<Member>> findAll() {
        List<Object[]> revisionWithMetaData = auditReader.createQuery()
                .forRevisionsOfEntityWithChanges(Member.class, true)
                .addOrder(AuditEntity.revisionProperty("revtstmp").desc())
                .getResultList();

        return revisionWithMetaData.stream()
                .map(RevisionWithMetadata::<Member>from)
                .toList();
    }

    public List<RevisionWithMetadata<Member>> findAllById(String id) {
        List<Object[]> revisionWithMetaData = auditReader.createQuery()
                .forRevisionsOfEntityWithChanges(Member.class, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.revisionProperty("revtstmp").desc())
                .getResultList();

        return revisionWithMetaData.stream()
                .map(RevisionWithMetadata::<Member>from)
                .toList();
    }

    public List<RevisionWithMetadata<Member>> findAllByName(String name) {
        List<Object[]> revisionWithMetaData = auditReader.createQuery()
                .forRevisionsOfEntityWithChanges(Member.class, true)
                .add(AuditEntity.property("name").eq(name))
                .addOrder(AuditEntity.revisionProperty("revtstmp").desc())
                .getResultList();

        return revisionWithMetaData.stream()
                .map(RevisionWithMetadata::<Member>from)
                .toList();
    }

    public List<Member> findAllByCondition(String id) {
        return auditReader.createQuery()
                .forRevisionsOfEntity(Member.class, true, true)
                .add(AuditEntity.id().eq(id))
                .add(AuditEntity.revisionType().eq(RevisionType.MOD)) // 리비전 타입 선택 가능 생성/수정/삭제
                .setFirstResult(0) // 페이징 조건
                .setMaxResults(2) // 페이징 조건
                .addOrder(AuditEntity.id().asc()) // 정렬 조건
                .getResultList();
    }

    public List<RevisionWithMetadata<Member>> findByIdWithMetadata(String id) {
        List<Object[]> revisionWithMetaData = auditReader.createQuery()
                .forRevisionsOfEntityWithChanges(Member.class, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.revisionProperty("revtstmp").desc())
                .getResultList();

        return revisionWithMetaData.stream()
                .map(RevisionWithMetadata::<Member>from)
                .toList();
    }

    public List<Member> findByIdHasChanges(String id) {
        return auditReader.createQuery()
                .forRevisionsOfEntity(Member.class, true, true)
                .add(AuditEntity.id().eq(id))
                .add(AuditEntity.property("name").hasChanged())
                .add(AuditEntity.property("age").hasNotChanged())
                .getResultList();
    }
}

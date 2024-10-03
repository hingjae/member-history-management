package com.honey.member_history_management.envers.repository;

import com.honey.member_history_management.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@RequiredArgsConstructor
public class MemberRevisionRepository {
    private final AuditReader auditReader;

    public List<Member> findAll() {
        return auditReader.createQuery()
                .forRevisionsOfEntity(Member.class, true, true)
                .getResultList();
    }
}

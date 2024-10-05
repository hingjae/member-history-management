package com.honey.member_history_management.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface MemberRepository extends JpaRepository<Member, String>, RevisionRepository<Member, String, Long> {
}

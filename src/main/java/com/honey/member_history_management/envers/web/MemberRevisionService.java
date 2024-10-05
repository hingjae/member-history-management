package com.honey.member_history_management.envers.web;

import com.honey.member_history_management.envers.repository.MemberRevisionRepository;
import com.honey.member_history_management.envers.repository.dto.RevisionWithMetadata;
import com.honey.member_history_management.envers.web.dto.SearchParam;
import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberRevisionService {
    private final MemberRevisionRepository memberRevisionRepository;
    private final MemberRepository memberRepository;

    public List<RevisionWithMetadata<Member>> findAll(SearchParam searchParam) {
        if (searchParam.getKeyword() == null || searchParam.getKeyword().isEmpty()) {
            return memberRevisionRepository.findAll();
        }
        return switch (searchParam.getSearchType()) {
            case ID -> memberRevisionRepository.findAllById(searchParam.getKeyword());
            case NAME -> memberRevisionRepository.findAllByName(searchParam.getKeyword());
            case null -> memberRevisionRepository.findAll();
        };
    }

    public List<RevisionWithMetadata<Member>> findByIdWithMetadata(String id) {
        return memberRevisionRepository.findByIdWithMetadata(id);
    }

    public void findLastChangeRevision(String id) {
        Revision<Long, Member> revision = memberRepository.findLastChangeRevision(id)
                .orElse(null);

        // 엔티티 조회
        Member entity = revision.getEntity();
        // 메타데이터 조회
        Optional<Instant> revisionInstant = revision.getRevisionInstant();
        Optional<Long> revisionNumber = revision.getRevisionNumber();
    }

    public Page<Revision<Long, Member>> findRevisionById(String id, Pageable pageable) {
        Page<Revision<Long, Member>> revisions = memberRepository.findRevisions(id, pageable);
        return revisions;
    }
}

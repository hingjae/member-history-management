package com.honey.member_history_management.envers.web;

import com.honey.member_history_management.envers.repository.dto.RevisionWithMetadata;
import com.honey.member_history_management.envers.web.dto.MemberRevisionListResponse;
import com.honey.member_history_management.envers.web.dto.SearchParam;
import com.honey.member_history_management.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberRevisionController {
    private final MemberRevisionService memberRevisionService;

    @GetMapping("/admin/member-revision")
    public String getMemberRevisions(@ModelAttribute("searchParam") SearchParam searchParam, Model model) {
        List<RevisionWithMetadata<Member>> revisionWithMetadataList = memberRevisionService.findAll(searchParam);

        MemberRevisionListResponse memberRevisions = MemberRevisionListResponse.from(revisionWithMetadataList);
        model.addAttribute("memberRevisions", memberRevisions.getItems());
        return "member-revision";
    }

    @GetMapping("/admin/member-revision/{memberId}")
    public String getMemberRevisionsById(@ModelAttribute("searchParam") SearchParam searchParam, @PathVariable("memberId") String memberId, Model model) {
        List<RevisionWithMetadata<Member>> revisionWithMetadataList = memberRevisionService.findByIdWithMetadata(memberId);

        MemberRevisionListResponse memberRevisions = MemberRevisionListResponse.from(revisionWithMetadataList);
        model.addAttribute("memberRevisions", memberRevisions.getItems());
        return "member-revision-detail";
    }
}

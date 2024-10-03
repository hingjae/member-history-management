package com.honey.member_history_management.member.api;

import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.MemberRepository;
import com.honey.member_history_management.member.domain.Role;
import com.honey.member_history_management.member.dto.MemberCreateForm;
import com.honey.member_history_management.team.api.TeamService;
import com.honey.member_history_management.team.domain.Team;
import com.honey.member_history_management.team.domain.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final TeamService teamService;

    public Member findById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public String create(MemberCreateForm form) {
        Team team = teamService.findById(form.getTeamId());
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        return memberRepository.save(form.toEntity(encodedPassword, Role.USER, team))
                .getId();
    }
}

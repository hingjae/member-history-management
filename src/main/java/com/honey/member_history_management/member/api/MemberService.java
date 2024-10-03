package com.honey.member_history_management.member.api;

import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.MemberRepository;
import com.honey.member_history_management.member.domain.Role;
import com.honey.member_history_management.member.dto.MemberForm;
import com.honey.member_history_management.team.api.TeamService;
import com.honey.member_history_management.team.domain.Team;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public String create(MemberForm form) {
        Team team = teamService.findById(form.getTeamId());
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        return memberRepository.save(form.toEntity(encodedPassword, Role.USER, team))
                .getId();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void modify(String memberId, MemberForm form) {
        Member member = findById(memberId);
        Team team = teamService.findById(form.getTeamId());
        member.modify(team, form.getName(), form.getAge(), form.getPhoneNumber());
    }

    @Transactional
    public void deleteById(String memberId) {
        memberRepository.deleteById(memberId);
    }
}

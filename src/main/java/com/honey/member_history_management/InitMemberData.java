package com.honey.member_history_management;

import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.MemberRepository;
import com.honey.member_history_management.member.domain.Role;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("local")
@RequiredArgsConstructor
@Component
public class InitMemberData {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void saveInitUser() {
        try {
            Member member = memberRepository.findById("admin")
                    .orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException e) {
            Member admin = Member.builder()
                    .id("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .name("admin")
                    .age(27)
                    .phoneNumber("010-1234-1234")
                    .role(Role.ADMIN)
                    .build();

            memberRepository.save(admin);
        }
    }
}

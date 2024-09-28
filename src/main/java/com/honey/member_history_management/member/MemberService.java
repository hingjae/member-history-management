package com.honey.member_history_management.member;

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

    public Member findById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public String create(MemberCreateForm form) {
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        log.info("encodedPassword: {}", encodedPassword);
        return memberRepository.save(form.toEntity(encodedPassword, Role.USER))
                .getId();
    }
}

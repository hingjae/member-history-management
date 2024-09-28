package com.honey.member_history_management;

import com.honey.member_history_management.member.Member;
import com.honey.member_history_management.member.MemberCreateForm;
import com.honey.member_history_management.member.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class InitMemberData {

    private final MemberService memberService;

    @PostConstruct
    @Transactional
    public void saveInitUser() {
        try {
            Member defaultMember = memberService.findById("user1");
        } catch (EntityNotFoundException e) {
            memberService.create(
                    MemberCreateForm.builder()
                            .id("user1")
                            .password("pw1")
                            .name("honey")
                            .age(27)
                            .phoneNumber("010-1234-1234")
                            .build()
            );
        }
    }
}

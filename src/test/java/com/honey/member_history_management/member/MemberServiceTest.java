package com.honey.member_history_management.member;

import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.MemberRepository;
import com.honey.member_history_management.member.domain.Role;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;

    @Rollback(value = false)
    @Test
    public void insertMember() {
        Member member = Member.builder()
                .id("user1")
                .password("pw1")
                .name("foo")
                .phoneNumber("010-1234-1234")
                .age(20)
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }

    @Rollback(value = false)
    @Transactional
    @Test
    public void updateMember() {
        Member member = memberRepository.findById("user1").get();

        member.setName("bar");
    }

    @Rollback(value = false)
    @Transactional
    @Test
    public void deleteMember() {
        memberRepository.deleteById("user1");
    }
}
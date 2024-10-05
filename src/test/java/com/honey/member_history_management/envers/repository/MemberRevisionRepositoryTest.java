package com.honey.member_history_management.envers.repository;

import com.honey.member_history_management.envers.repository.dto.RevisionWithMetadata;
import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class MemberRevisionRepositoryTest {

    @Autowired
    private MemberRevisionRepository memberRevisionRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findAll() {
        Member member = Member.builder()
                .id("user1")
                .password("pw1")
                .name("foo")
                .age(20)
                .phoneNumber("010-1234-1234")
                .build();

        memberRepository.save(member);

        List<RevisionWithMetadata<Member>> all = memberRevisionRepository.findAll();

        System.out.println(all);
    }
}
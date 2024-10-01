package com.honey.member_history_management.member.controller;

import com.honey.member_history_management.FormDataEncoder;
import com.honey.member_history_management.member.Member;
import com.honey.member_history_management.member.MemberCreateForm;
import com.honey.member_history_management.member.MemberService;
import com.honey.member_history_management.security.CustomUserDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(FormDataEncoder.class)
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FormDataEncoder formDataEncoder;
    @Autowired
    private MemberService memberService;

    @Transactional
    @Test
    public void auditCreateBy() throws Exception {
        MemberCreateForm form = MemberCreateForm.builder()
                .id("user1")
                .password("pw1")
                .name("foo")
                .age(20)
                .phoneNumber("010-1234-1234")
                .build();

        mockMvc.perform(post("/members/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(formDataEncoder.encode(form))
                .with(csrf())
                .with(user(
                        CustomUserDetails.builder()
                                .username("admin")
                                .build()
                ))
        );

        Member findMember = memberService.findById("user1");

        assertThat(findMember.getId()).isEqualTo("user1");
        assertThat(findMember.getName()).isEqualTo("foo");
        assertThat(findMember.getAge()).isEqualTo(20);
        assertThat(findMember.getPhoneNumber()).isEqualTo("010-1234-1234");
        assertThat(findMember.getCreatedBy()).isEqualTo("admin");
    }

}
package com.honey.member_history_management.member.controller;

import com.honey.member_history_management.member.MemberCreateForm;
import com.honey.member_history_management.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/create")
    public String createForm(@ModelAttribute("memberCreateForm") MemberCreateForm form) {
        return "sign-up";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("memberCreateForm") MemberCreateForm form) {
        memberService.create(form);
        return "redirect:/";
    }
}

package com.honey.member_history_management.member.controller;

import com.honey.member_history_management.member.MemberCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
public class MemberController {

    @GetMapping("/create")
    public String createForm(@ModelAttribute("memberCreateForm") MemberCreateForm form) {
        return "sign-up";
    }
}

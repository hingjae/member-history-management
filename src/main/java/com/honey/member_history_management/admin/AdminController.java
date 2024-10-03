package com.honey.member_history_management.admin;

import com.honey.member_history_management.member.api.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String admin(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "members";
    }
}

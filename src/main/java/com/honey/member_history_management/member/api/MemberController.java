package com.honey.member_history_management.member.api;

import com.honey.member_history_management.member.dto.MemberCreateForm;
import com.honey.member_history_management.team.api.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("/create")
    public String createForm(@ModelAttribute("memberCreateForm") MemberCreateForm form, Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "sign-up";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("memberCreateForm") MemberCreateForm form) {
        memberService.create(form);
        return "redirect:/";
    }
}

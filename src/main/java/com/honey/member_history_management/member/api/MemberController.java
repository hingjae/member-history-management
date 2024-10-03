package com.honey.member_history_management.member.api;

import com.honey.member_history_management.member.domain.Member;
import com.honey.member_history_management.member.dto.MemberForm;
import com.honey.member_history_management.team.api.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("/create")
    public String createForm(@ModelAttribute("memberForm") MemberForm form, Model model) {
        model.addAttribute("isModify", false);
        model.addAttribute("teams", teamService.findAll());
        return "member-form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("memberForm") MemberForm form) {
        memberService.create(form);
        return "redirect:/";
    }

    @GetMapping("/{memberId}/modify")
    public String modifyForm(@PathVariable("memberId") String memberId, Model model) {
        Member member = memberService.findById(memberId);
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("memberForm", MemberForm.from(member));
        model.addAttribute("isModify", true);
        return "member-form";
    }

    @PostMapping("/{memberId}/modify")
    public String modify(@PathVariable("memberId") String memberId, @ModelAttribute("memberForm") MemberForm form) {
        memberService.modify(memberId, form);
        return "redirect:/home";
    }

    @PostMapping("/{memberId}/delete")
    public String delete(@PathVariable("memberId") String memberId) {
        memberService.deleteById(memberId);
        return "redirect:/home";
    }
}

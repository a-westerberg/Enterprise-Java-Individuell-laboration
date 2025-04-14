package com.skrt.inviduelllab.controllers;

import com.skrt.inviduelllab.entities.Member;
import com.skrt.inviduelllab.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MemberService memberService;

    @Autowired
    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/addmember")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @PutMapping("/updatemember")
    public ResponseEntity<Member> updateMember(@RequestBody Member updatedMember) {
        return memberService.updateMember(updatedMember);
    }

    @DeleteMapping("/deletemember/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member with ID: {" + id + "} successfully deleted");
    }

    @GetMapping("/deletemember")
    public String showDeleteMemberPage(Model model) {
        List<Member> members = memberService.getAllMembers().getBody();
        model.addAttribute("members", members);
        return "deletemember";
    }

    @PostMapping("/deletememberbyid")
    public String deleteMemberByIdHtml(@RequestParam Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/deletemember";
    }

}

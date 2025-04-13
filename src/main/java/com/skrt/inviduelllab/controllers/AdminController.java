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

    // TODO KOD För att skapa en ny address om man lägger in en ny member med en adress som inte finns i repo


    @PutMapping("/updatemember")
    public ResponseEntity<Member> updateMember(@RequestBody Member updatedMember) {
        return memberService.updateMember(updatedMember);
    }

    // TODO Fixa ett return när man kör denna
    @DeleteMapping("/deletemember/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }

        //TODO SE över detta om denna är korrekt enligt pdf/kursen
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

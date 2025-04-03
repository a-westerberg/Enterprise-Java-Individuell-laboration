package com.skrt.inviduelllab.controllers;

import com.skrt.inviduelllab.entities.Member;
import com.skrt.inviduelllab.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
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
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }



}

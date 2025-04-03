package com.skrt.inviduelllab.services;

import com.skrt.inviduelllab.entities.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface MemberService {

    ResponseEntity<List<Member>> getAllMembers();
    ResponseEntity<Member> getMemberById(Long id);
    ResponseEntity<Member> addMember(Member member);
    ResponseEntity<Member> updateMember(Member updatedMember);
    ResponseEntity <Void> deleteMember(Long id);
}

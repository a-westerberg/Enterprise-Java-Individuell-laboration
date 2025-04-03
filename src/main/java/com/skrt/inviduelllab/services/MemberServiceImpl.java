package com.skrt.inviduelllab.services;

import com.skrt.inviduelllab.entities.Member;
import com.skrt.inviduelllab.exceptions.ResourceNotFoundException;
import com.skrt.inviduelllab.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<Member>> getMemberById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isPresent()) {
            return new ResponseEntity<>(optionalMember, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Member", "id", id);
        }
    }
    // TODO fixa logik för om igen address finns så den läggs till isf
    @Override
    public ResponseEntity<Member> addMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Member> updateMember(Member updatedMember) {
        Long id = updatedMember.getId();

        return memberRepository.findById(id)
                .map(member -> {
                    member.setFirstName(updatedMember.getFirstName());
                    member.setLastName(updatedMember.getLastName());
                    member.setEmail(updatedMember.getEmail());
                    member.setPhone(updatedMember.getPhone());
                    member.setAddress(updatedMember.getAddress());
                    member.setDateOfBirth(updatedMember.getDateOfBirth());
                    member.setAddress(updatedMember.getAddress());

                    Member savedMember = memberRepository.save(member);
                    return new ResponseEntity<>(savedMember, HttpStatus.OK);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Member", "id", id));
    }

    @Override
    public ResponseEntity<Void> deleteMember(Long id) {
        if(!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member", "id", id);
        }
        memberRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

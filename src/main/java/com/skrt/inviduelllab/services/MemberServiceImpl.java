package com.skrt.inviduelllab.services;

import com.skrt.inviduelllab.entities.Address;
import com.skrt.inviduelllab.entities.Member;
import com.skrt.inviduelllab.exceptions.ResourceNotFoundException;
import com.skrt.inviduelllab.exceptions.UniqueValueException;
import com.skrt.inviduelllab.repositories.AddressRepository;
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
    private  AddressRepository addressRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, AddressRepository addressRepository) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Member> getMemberById(Long id){
        return memberRepository.findById(id)
                .map(member -> new ResponseEntity<>(member, HttpStatus.OK))
                .orElseThrow(()-> new ResourceNotFoundException("Member", "id", id));
    }

    @Override
    public ResponseEntity<Member> addMember(Member member) {
        if(memberRepository.existsByEmail(member.getEmail())){
            throw new UniqueValueException("Email", member.getEmail());
        }
        if(memberRepository.existsByPhone(member.getPhone())){
            throw new UniqueValueException("Phone", member.getPhone());
        }
        Address address = member.getAddress();
        Optional<Address> existingAddress = addressRepository.findByStreetAndPostalCodeAndCity(
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
        if(existingAddress.isPresent()) {
            member.setAddress(existingAddress.get());
        } else {
            addressRepository.save(address);
        }
        Member savedMember = memberRepository.save(member);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Member> updateMember(Member updatedMember) {
        Long id = updatedMember.getId();
        return memberRepository.findById(id)
                .map(member -> {
                    if(updatedMember.getEmail() != null && !updatedMember.getEmail().equals(member.getEmail())) {
                        if(memberRepository.existsByEmail(updatedMember.getEmail())){
                            throw new UniqueValueException("Email", updatedMember.getEmail());
                        }
                        member.setEmail(updatedMember.getEmail());
                    }
                    if(updatedMember.getPhone() != null && !updatedMember.getPhone().equals(member.getPhone())) {
                        if(memberRepository.existsByPhone(updatedMember.getPhone())){
                            throw new UniqueValueException("Phone", updatedMember.getPhone());
                        }
                        member.setPhone(updatedMember.getPhone());
                    }
                    Address updatedAddress = updatedMember.getAddress();
                    Optional<Address> existingAddress = addressRepository.findByStreetAndPostalCodeAndCity(
                            updatedAddress.getStreet(),
                            updatedAddress.getPostalCode(),
                            updatedAddress.getCity()
                    );
                    if(existingAddress.isPresent()) {
                        member.setAddress(existingAddress.get());
                    } else {
                        Address savedAddress = addressRepository.save(updatedAddress);
                        member.setAddress(savedAddress);
                    }
                    member.setFirstName(updatedMember.getFirstName());
                    member.setLastName(updatedMember.getLastName());
                    member.setDateOfBirth(updatedMember.getDateOfBirth());

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

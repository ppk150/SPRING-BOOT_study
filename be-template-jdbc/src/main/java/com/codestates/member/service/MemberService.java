package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.hello_world.MessageRepository;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * V2
 *  - 메서드 구현
 *  - DI 적용
 */
@Service
public class MemberService {

    private MemberRepository memberRepository;

    //1
    public MemberService (MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    public Member createMember(Member member) {
        // TODO should business logic

        //2
        verifyExistsEmail(member.getEmail());

        //3
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        // TODO should business logic

        //4
        Member findMember = findVerifiedMember(member.getMemberId());

        //5
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        //6
        return memberRepository.save(findMember);
    }

    //7
    public Member findMember(long memberId) {
        // TODO should business logic

        return findVerifiedMember(memberId);
    }

    public List<Member> findMembers() {
        // TODO should business logic
        //8
        return (List<Member>) memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        // TODO should business logic

        Member findMember = findVerifiedMember(memberId);
        //9
        memberRepository.delete(findMember);
    }

    //10
    public Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    //11
    private void verifyExistsEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}

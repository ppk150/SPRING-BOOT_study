package com.codestates.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DBMemberService implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public DBMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        Member savedMember  = memberRepository.save(member);

        System.out.println("# Create Member in DB");

        return savedMember ;
    }
}

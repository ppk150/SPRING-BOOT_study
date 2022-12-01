package com.codestates.member;


//import java.lang.reflect.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Member creatMember (Member member){

        // member객체는 나중에 DB 저장 후 되돌려 받는 용도로 변경 필요

        Member creatMember = member;
        return creatMember;
    }

    public Member updateMember(Member member){
        // member객체는 나중에 DB 저장 후 되돌려 받는 용도로 변경 필요
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember (long memberId){

        // member 객체는 나중에 DB에서 조회 하는것으로 변경 필요

        Member member = new Member(memberId, "hgd@gmail.com", "홍길동", "010-1234-5678");
        return member;
    }

    public List<Member> findMembers(){

        List<Member> members = List.of(

                new Member(1 , "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2 , "lml@gmail.com", "이몽룡", "010-1111-2222")

        );

        return members;

    }

    public void deleteMember(long memberId){

    }



}

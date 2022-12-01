/*package com.codestates.member;

import org.springframework.stereotype.Component;

@Component // 1
public class MemberMapper {

    // 2 MemberPostDto를 Member로 전환
    public Member memberPostDtoToMember(MemberPostDto memberPostDto ){
        return new Member(0L,
                memberPostDto.getEmail(),
                memberPostDto.getName(),
                memberPostDto.getPhone()
        );
    }

    // 3MemberPatchDto를 Member로 변환

    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto){

        return new Member(
                memberPatchDto.getMemberId(),null, memberPatchDto.getName(), memberPatchDto.getPhone()
        );
    }

    // 4 Member 를 MemberResponseDto로 변환

    public MemberResponseDto memberToMemberResponseDto(Member member){
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone()
        );
    }

}
*/
package com.codestates.member;

import com.codestates.member.MemberPatchDto;
import com.codestates.member.MemberPostDto;
import com.codestates.member.MemberResponseDto;
import com.codestates.member.Member;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring") // 1
public interface MemberMapper {
    Member memberPostDtoToMember (MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    MemberResponseDto memberToMemberResponseDto (Member member);
}


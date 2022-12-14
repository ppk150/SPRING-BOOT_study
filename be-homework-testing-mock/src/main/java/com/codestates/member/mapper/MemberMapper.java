package com.codestates.member.mapper;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post requestBody);
    Member memberPatchToMember(MemberDto.Patch requestBody);
    MemberDto.response memberToMemberResponse(Member member);
    List<MemberDto.response> membersToMemberResponses(List<Member> members);
}

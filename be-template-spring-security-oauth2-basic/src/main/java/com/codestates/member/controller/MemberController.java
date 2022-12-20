//package com.codestates.member.controller;
//
//import com.codestates.dto.MultiResponseDto;
//import com.codestates.dto.SingleResponseDto;
//import com.codestates.member.dto.MemberDto;
//import com.codestates.member.entity.Member;
//import com.codestates.member.mapper.MemberMapper;
//import com.codestates.member.service.MemberService;
//import com.codestates.stamp.Stamp;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.Positive;
//import java.util.List;
//
//
///**
// * - DI 적용
// * - Mapstruct Mapper 적용
// * - @ExceptionHandler 적용
// */
//@RestController
//@RequestMapping("/v11/members")
//@Validated
//@Slf4j
//public class MemberController {
//    private final MemberService memberService;
//    private final MemberMapper mapper;
//
//    public MemberController(MemberService memberService, MemberMapper mapper) {
//        this.memberService = memberService;
//        this.mapper = mapper;
//    }
//
//    @PostMapping
//    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
//        Member member = mapper.memberPostToMember(requestBody);
//        member.setStamp(new Stamp()); // homework solution 추가
//
//        Member createdMember = memberService.createMember(member);
//        MemberDto.Response response = mapper.memberToMemberResponse(createdMember);
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(response),
//                HttpStatus.CREATED);
//    }
//
//    @PatchMapping("/{member-id}")
//    public ResponseEntity patchMember(
//            @PathVariable("member-id") @Positive long memberId,
//            @Valid @RequestBody MemberDto.Patch requestBody) {
//        requestBody.setMemberId(memberId);
//
//        Member member =
//                memberService.updateMember(mapper.memberPatchToMember(requestBody));
//
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(mapper.memberToMemberResponse(member)),
//                HttpStatus.OK);
//    }
//
//    @GetMapping("/{member-id}")
//    public ResponseEntity getMember(
//            @PathVariable("member-id") @Positive long memberId) {
//        Member member = memberService.findMember(memberId);
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(mapper.memberToMemberResponse(member))
//                , HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity getMembers(@Positive @RequestParam int page,
//                                     @Positive @RequestParam int size) {
//        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
//        List<Member> members = pageMembers.getContent();
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.membersToMemberResponses(members),
//                        pageMembers),
//                HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{member-id}")
//    public ResponseEntity deleteMember(
//            @PathVariable("member-id") @Positive long memberId) {
//        memberService.deleteMember(memberId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}

package com.codestates.member.controller;

import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


/**
 * - DI 적용
 * - Mapstruct Mapper 적용
 * - @ExceptionHandler 적용
 */
@RestController
@RequestMapping("/v11/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    // TODO OAuth 2 인증 환경에서는 회원 정보를 별도로 관리하지 않으므로, 회원 정보를 어떻게 로드할 것인가는 추가적인 논의가 필요합니다.
}
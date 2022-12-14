package com.codestates.homework;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.repository.MemberRepository;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.member.entity.Member.MemberStatus.MEMBER_ACTIVE;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberMapper mapper;

    @MockBean
    private MemberRepository repository;

//    @Autowired
//    private Member member;

    @Test
    void patchMemberTest() throws Exception {
        // TODO MemberController의 patchMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
        // given
        MemberDto.Patch patch = new MemberDto.Patch(1L,
                "홍길동",
                "010-1234-5678",
                MEMBER_ACTIVE
        );

        Member member = mapper.memberPatchToMember(patch); //3
        member.setStamp(new Stamp()); // 4

        // 5
        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(member);

        String content = gson.toJson(patch);

        //when
        ResultActions actions =
                mockMvc.perform(
                        MockMvcRequestBuilders.patch("/v11/members/" + 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        //then
        MvcResult result = actions
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.email").value(patch.getEmail()))
                .andExpect(jsonPath("$.data.name").value(patch.getName()))
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()))
                .andReturn();



    }

    @Test
    void getMemberTest() throws Exception {
        // TODO MemberController의 getMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^

        // given

        Member member1 = new Member();
        member1.setMemberId(1L);
        member1.setName("표세웅");
        member1.setPhone("010-9677-8211");
        member1.setEmail("vytpdnd11@naver.com");
        member1.setMemberStatus(MEMBER_ACTIVE);

        long memberId = 1 ;

        // (5)
        given(memberService.findMember(Mockito.any(Member.class).getMemberId()))
                .willReturn(member1);

        String content = gson.toJson(member1);

        ResultActions actions =
                mockMvc.perform(
                        get("/v11/members/"+1 )
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(member1.getEmail()))
                .andExpect(jsonPath("$.data.name").value(member1.getName()))
                .andExpect(jsonPath("$.data.phone").value(member1.getPhone()))
                .andReturn();



    }

    @Test
    void getMembersTest() throws Exception {
        // TODO MemberController의 getMembers() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
    }

    @Test
    void deleteMemberTest() throws Exception {
        // TODO MemberController의 deleteMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^


    }
}

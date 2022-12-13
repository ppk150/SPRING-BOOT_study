package com.codestates.homework;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.member.entity.Member.MemberStatus.MEMBER_ACTIVE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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



    @Autowired
    MemberService memberService;

    @Autowired
    private MemberRepository repository;

    @BeforeEach
    public void init() {

        Member member = new Member("hdg@gmail.com");
        member.setPhone("010-1111-1111");
        member.setName("홍길동");
        member.setMemberId(1L);
//        repository.save(member);

        Stamp stamp = new Stamp();

        member.setStamp(stamp);

        Member member2 = new Member("vytpdnd@gmail.com");
        member2.setPhone("010-9677-1111");
        member2.setName("표세웅");
        member2.setMemberId(2L);
//        repository.save(member);

        Stamp stamp2 = new Stamp();
        member2.setStamp(stamp2);

        memberService.createMember(member);
        memberService.createMember(member2);
    }
    @Test
    void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");
        String content = gson.toJson(post);


        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/v11/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        MvcResult result = actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void patchMemberTest() throws Exception {
        // TODO MemberController의 patchMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.

        // given
        Member member = repository.findByEmail("hdg@gmail.com").get();

        MemberDto.Patch patch = new MemberDto.Patch(member.getMemberId(), member.getName(),"010-2111-1111"
        ,Member.MemberStatus.MEMBER_ACTIVE);


//        MemberDto.Patch patch = new MemberDto.Patch(1,
//                "홍길동",
//                "010-1234-7678",
//                Member.MemberStatus.MEMBER_ACTIVE);
        String content = gson.toJson(patch);

        // when
        ResultActions actions =
                mockMvc.perform(
                        MockMvcRequestBuilders.patch("/v11/members/"+ 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        MvcResult result = actions
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.m").value(patch.getMemberStatus()))
                .andExpect(jsonPath("$.data.name").value(patch.getName()))
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()))
                .andReturn();
    }

    @Test
    void getMemberTest() throws Exception {
        // TODO MemberController의 getMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.

        long memberId = 1 ;


        Member member = repository.findById(memberId).get();

        MemberDto.response response = new MemberDto.response(member.getMemberId(), member.getEmail(),member.getName(), member.getPhone()
                ,Member.MemberStatus.MEMBER_ACTIVE,member.getStamp());

        String content = gson.toJson(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/v11/members/"+memberId )
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(response.getEmail()))
                .andExpect(jsonPath("$.data.name").value(response.getName()))
                .andExpect(jsonPath("$.data.phone").value(response.getPhone()))
                .andReturn();

    }

    @Test
    void getMembersTest() throws Exception {
        // TODO MemberController의 getMembers() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.





    }

    @Test
    void deleteMemberTest() throws Exception {
        // TODO MemberController의 deleteMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.

        long memberId = 1 ;


        Member member = repository.findById(memberId).get();

        MemberDto.response response = new MemberDto.response(member.getMemberId(), member.getEmail(),member.getName(), member.getPhone()
                ,Member.MemberStatus.MEMBER_ACTIVE,member.getStamp());
        String content = gson.toJson(response);

        // when

        ResultActions actions =
                mockMvc.perform(
                        delete("/v11/members/"+memberId )
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)

                );
        assertNull(member.getEmail(), "이메일이 삭제되지 않았습니다.");
        assertNull(member.getName(), "이름이 삭제되지 않았습니다.");
        assertNull(member.getPhone(), "전화번호가 삭제되지 않았습니다.");


    }
}

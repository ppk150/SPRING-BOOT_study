package com.codestates.restdocs;


import com.codestates.member.controller.MemberController;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.List;


import static com.codestates.restdocs.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.restdocs.ApiDocumentUtils.getResponsePreProcessor;
import static com.codestates.util.ApiDocumentUtils.getDocumentRequest;
import static com.codestates.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    // 1
    @MockBean
    private MemberService memberService;

    // 2
    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void postMemberTest() throws Exception {
        // (3) given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com", "?????????", "010-1234-5678");
        String content = gson.toJson(post);

        // (4)
        MemberDto.response responseDto =
                new MemberDto.response(1L,
                        "hgd@gmail.com",
                        "?????????",
                        "010-1234-5678",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());

        // (5)
        given(mapper.memberPostToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());

        // (6)
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());

        // (7)
        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(responseDto);

        // (8) when
        ResultActions actions =
                mockMvc.perform(
                        post("/v11/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
                .andDo(document(       // (9)
                        "post-member",     // (9-1)
                        getRequestPreProcessor(),      // (9-2)
                        getResponsePreProcessor(),     // (9-3)
                        requestFields(             // (9-4)
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"), // (9-5)
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("????????? ??????")
                                )
                        ),
                        responseFields(        // (9-6)
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"), // (9-7)
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data.phone").type(JsonFieldType.STRING).description("????????? ??????"),
                                        fieldWithPath("data.memberStatus").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.stamp").type(JsonFieldType.NUMBER).description("????????? ??????")
                                )
                        )
                ));
    }

    @Test
    public void patchMemberTest() throws Exception {
        // given
        long memberId = 1L;
        MemberDto.Patch patch = new MemberDto.Patch(memberId, "?????????", "010-1111-1111", Member.MemberStatus.MEMBER_ACTIVE);
        String content = gson.toJson(patch);

        MemberDto.response responseDto =
                new MemberDto.response(1L,
                        "hgd@gmail.com",
                        "?????????",
                        "010-1111-1111",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());

        // willReturn()??? ????????? null??? ???????????? ??????.
        given(mapper.memberPatchToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());

        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());

        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/v11/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(patch.getMemberId()))
                .andExpect(jsonPath("$.data.name").value(patch.getName()))
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()))
                .andExpect(jsonPath("$.data.memberStatus").value(patch.getMemberStatus().getStatus()))
                .andDo(document("patch-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(              // (1)
                                parameterWithName("member-id").description("?????? ?????????")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????").ignored(),    // (2)
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("??????").optional(),    // (3)
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("????????? ??????").optional(),
                                        fieldWithPath("memberStatus").type(JsonFieldType.STRING).description("?????? ??????: MEMBER_ACTIVE / MEMBER_SLEEP / MEMBER_QUIT").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data.phone").type(JsonFieldType.STRING).description("????????? ??????"),
                                        fieldWithPath("data.memberStatus").type(JsonFieldType.STRING).description("?????? ??????: ????????? / ?????? ?????? / ?????? ??????"),
                                        fieldWithPath("data.stamp").type(JsonFieldType.NUMBER).description("????????? ??????")
                                )
                        )
                ));
    }
}

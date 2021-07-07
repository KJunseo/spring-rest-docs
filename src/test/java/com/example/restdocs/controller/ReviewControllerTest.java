package com.example.restdocs.controller;

import com.example.restdocs.dto.request.ReviewCreateRequest;
import com.example.restdocs.restdocs.common.RestApiDocumentTest;

import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewControllerTest extends RestApiDocumentTest {

    private static final String JWT_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @Test
    @DisplayName("새로운 리뷰 등록")
    void newReview() throws Exception {
        // given
        String body = objectMapper.writeValueAsString(
                new ReviewCreateRequest(1L, 2L, "title1", "content1", "https://github.com/KJunseo"));

        // when
        ResultActions result = mockMvc.perform(
                post("/reviews")
                        .header("Authorization", JWT_TOKEN)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(header().string("Location", "/reviews/1"))
              .andExpect(status().isCreated())
              .andDo(document("reviews/create",
                      requestHeaders(headerWithName("Authorization").description("jwt login header")),
                      requestFields(
                              fieldWithPath("studentId").description("학생 id"),
                              fieldWithPath("teacherId").description("선생님 id"),
                              fieldWithPath("title").description("리뷰 제목"),
                              fieldWithPath("content").description("리뷰 요청 설명"),
                              fieldWithPath("prUrl").description("pr 링크")
                      ),
                      responseHeaders(headerWithName("Location").description("review detail resource id"))
              ));
    }

    @Test
    @DisplayName("내가 요청한 리뷰 목록")
    void studentReviews() throws Exception {
        // given
        FieldDescriptor[] reviews = getReviewsFieldDescriptors();

        // when
        ResultActions result = mockMvc.perform(
                get("/reviews/student/{id}", 1)
                        .header("Authorization", JWT_TOKEN));

        // then
        result.andExpect(status().isOk())
              .andDo(document("reviews/student-search",
                      requestHeaders(headerWithName("Authorization").description("jwt login header")),
                      responseFields(reviews), pathParameters(
                              parameterWithName("id").description("리뷰 id")
                      )));
    }

    @Test
    @DisplayName("내가 리뷰한 목록")
    void teacherReviews() throws Exception {
        // given
        FieldDescriptor[] reviews = getReviewsFieldDescriptors();

        // when
        ResultActions result = mockMvc.perform(
                get("/reviews/teacher/{id}", 1));

        // then
        result.andExpect(status().isOk())
              .andDo(document("reviews/teacher-search",
                      responseFields(reviews),
                      pathParameters(
                              parameterWithName("id").description("리뷰 id")
                      )));
    }

    @Test
    @DisplayName("리뷰 상세 페이지")
    void reviewDetail() throws Exception {
        // given
        FieldDescriptor[] review = getReviewFieldDescriptors();

        // when
        ResultActions result = mockMvc.perform(
                get("/reviews/{id}", 1));

        // then
        result.andExpect(status().isOk())
              .andDo(document("reviews/detail",
                      pathParameters(
                              parameterWithName("id").description("리뷰 id")
                      ),
                      responseFields(review)
              ));
    }

    @Test
    @DisplayName("리뷰 상태 변경")
    void changeReviewProgress() throws Exception {
        // when
        ResultActions result = mockMvc.perform(patch("/reviews/{id}", 1)
                .header("Authorization", JWT_TOKEN));

        // then
        result.andExpect(status().isNoContent())
              .andDo(document("reviews/change",
                      requestHeaders(headerWithName("Authorization").description("jwt login header")),
                      pathParameters(
                              parameterWithName("id").description("리뷰 id")
                      )
              ));
    }

    private FieldDescriptor[] getReviewFieldDescriptors() {
        return new FieldDescriptor[]{
                fieldWithPath("id").description("리뷰 id"),
                fieldWithPath("title").description("리뷰 제목"),
                fieldWithPath("content").description("리뷰 요청 상세 내용"),
                fieldWithPath("progress").description("link:common/progress.html[리뷰 상태,role='popup']"),
                fieldWithPath("teacherProfile.id").description("선생님 id"),
                fieldWithPath("teacherProfile.name").description("선생님의 닉네임"),
                fieldWithPath("teacherProfile.imageUrl").description("선생님의 github 프로필 이미지 url"),
                fieldWithPath("studentProfile.id").description("학생 Id"),
                fieldWithPath("studentProfile.name").description("학생 닉네임"),
                fieldWithPath("studentProfile.imageUrl").description("학생의 github 프로필 이미지 url")
        };
    }

    private FieldDescriptor[] getReviewsFieldDescriptors() {
        return new FieldDescriptor[]{
                fieldWithPath("reviews[].id").description("리뷰 id"),
                fieldWithPath("reviews[].title").description("리뷰 제목"),
                fieldWithPath("reviews[].content").description("리뷰 요청 상세 내용"),
                fieldWithPath("reviews[].progress").description("link:common/progress.html[리뷰 상태,role='popup']"),
                fieldWithPath("reviews[].teacherProfile.id").description("선생님 id"),
                fieldWithPath("reviews[].teacherProfile.name").description("선생님의 닉네임"),
                fieldWithPath("reviews[].teacherProfile.imageUrl").description("선생님의 github 프로필 이미지 url"),
                fieldWithPath("reviews[].studentProfile.id").description("학생 Id"),
                fieldWithPath("reviews[].studentProfile.name").description("학생 닉네임"),
                fieldWithPath("reviews[].studentProfile.imageUrl").description("학생의 github 프로필 이미지 url")
        };
    }
}

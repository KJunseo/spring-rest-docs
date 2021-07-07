package com.example.restdocs.restdocs.common;

import java.util.Arrays;
import java.util.Map;

import com.example.restdocs.domain.Progress;
import com.example.restdocs.restdocs.utils.CustomResponseFieldsSnippet;

import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommonDocumentTest extends RestApiDocumentTest {

    @Test
    @DisplayName("상태값 문서 생성")
    public void progress() throws Exception {
        //when
        ResultActions result = mockMvc.perform(
                get("/progress")
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
              .andDo(document("common",
                      customResponseFields("custom-response", beneathPath("progresses"), // (2)
                              attributes(key("title").value("상태")),
                              enumConvertFieldDescriptor(Progress.values())
                      )
              ));

    }

    private FieldDescriptor[] enumConvertFieldDescriptor(Progress[] enumTypes) {
        return Arrays.stream(enumTypes)
                     .map(enumType -> fieldWithPath(enumType.name()).description(enumType.getDescription()))
                     .toArray(FieldDescriptor[]::new);
    }

    public static CustomResponseFieldsSnippet customResponseFields(String type,
                                                                   PayloadSubsectionExtractor<?> subsectionExtractor,
                                                                   Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes
                , true);
    }

}

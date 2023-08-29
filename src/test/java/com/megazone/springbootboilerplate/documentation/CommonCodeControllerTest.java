package com.megazone.springbootboilerplate.documentation;

import com.megazone.springbootboilerplate.common.dto.CommonResponseType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;

public class CommonCodeControllerTest extends Documentation {

    @Test
    void codes() throws Exception {
        mockMvc.perform(get("/codes")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(document("code-response",
                CodeResponseFieldsSnippet.of("code-response", fieldDescriptors())));
    }

    private List<FieldDescriptor> fieldDescriptors() {
        return Arrays.stream(CommonResponseType.values())
            .map(code -> fieldWithPath(code.name())
                .type(JsonFieldType.OBJECT)
                .attributes(
                    key("code").value(code.getCode()),
                    key("message").value(code.getMessage()),
                    key("status").value(code.getStatus())
                )
            ).toList();
    }
}

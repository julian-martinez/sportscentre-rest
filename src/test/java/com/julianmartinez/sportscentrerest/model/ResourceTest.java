package com.julianmartinez.sportscentrerest.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class ResourceTest {

    private static final Resource OBJECT = Resource.builder()
            .id(1)
            .name("Tennis A")
            .build();

    //language=JSON
    private static final String JSON = "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"Tennis A\"\n" +
            "}";

    @Autowired
    private JacksonTester<Resource> jacksonTester;

    @Test
    void shouldSerialize() throws IOException {
        JsonContent<Resource> jsonContent = jacksonTester.write(OBJECT);
        assertThat(jsonContent).isEqualToJson(JSON);
    }

    @Test
    void shouldDeserialize() throws IOException {
        ObjectContent<Resource> objectContent = jacksonTester.parse(JSON);
        assertThat(objectContent.getObject()).isEqualTo(OBJECT);
    }

}
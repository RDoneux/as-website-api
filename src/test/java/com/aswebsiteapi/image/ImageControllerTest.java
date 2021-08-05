package com.aswebsiteapi.image;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willReturn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import com.aswebsiteapi.util.AbstractMockMvcTest;
import com.aswebsiteapi.util.TestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@AutoConfigureMockMvc
@WebMvcTest(ImageRepository.class)
public class ImageControllerTest extends AbstractMockMvcTest {

    private static final String TEST_ENDPOINT = "/image";
    private static final String TEST_IMAGE_URL = "test-image-url";

    private MockMvc mockMvc;

    @BeforeEach
    void setupMockMvc() throws JsonProcessingException {
        var imageController = new ImageController(imageRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).setControllerAdvice(ImageControllerAdvice.class)
                .build();
    }

    @Test
    void shouldReturn400WhenIMageIdIsNotValidUUIDForGet() throws Exception {
        var id = "invalid-UUID";

        var response = mockMvc
                .perform(get(TEST_ENDPOINT + "/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.serializeObject(createValidImage().build())))
                .andExpect(status().isBadRequest()).andReturn();

        var responseAsString = response.getResponse().getContentAsString();

        assertThat(responseAsString).isNotNull();
        assertThat(JsonPath.<String>read(responseAsString, "$.message")).isEqualTo("Validation failed");
        assertThat(JsonPath.<String>read(responseAsString, "$.errors[0].field")).isEqualTo("imageId");
        assertThat(JsonPath.<String>read(responseAsString, "$.errors[0].error"))
                .contains("invalid-UUID is not a valid UUID");
    }

    @Test
    void shouldReturn404NotFoundWhenImageDoesNotExsistOnGet() throws Exception {
        var id = UUID.randomUUID();

        var response = mockMvc
                .perform(get(TEST_ENDPOINT + "/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.serializeObject(createValidImage().build())))
                .andExpect(status().isNotFound()).andReturn();

        var responseAsString = response.getResponse().getContentAsString();

        assertThat(responseAsString).isNotEmpty();
        assertThat(JsonPath.<String>read(responseAsString, "$.message"))
                .isEqualTo("Image with id \"" + id + "\" not found");
    }

    private static ImageEntityBuilder createValidImage() {
        return ImageEntity.builder().image_url(TEST_IMAGE_URL);
    }

    @Test
    void shouldReturn200OKWhenGettingImageEntity() throws Exception {
        var id = UUID.randomUUID();

        willReturn(Optional.of(createValidImage().id(id).build())).given(imageRepository).findById(id);

        var response = mockMvc.perform(get(TEST_ENDPOINT + "/" + id)).andExpect(status().isOk()).andReturn();

        var unpackedResponse = response.getResponse().getContentAsString();

        assertThat(unpackedResponse).isNotEmpty();
        assertThat(unpackedResponse).contains(id.toString());
        assertThat(unpackedResponse).contains(TEST_IMAGE_URL);
    }

    @Test
    void shouldReturn200OKWhenGettingAllImages() throws Exception {

        var id1 = UUID.randomUUID();
        var id2 = UUID.randomUUID();

        willReturn(List.of(ImageEntity.builder().id(id1).build(), ImageEntity.builder().id(id2).build()))
                .given(imageRepository).findAll();

        var response = mockMvc.perform(get(TEST_ENDPOINT)).andExpect(status().isOk()).andReturn();

        var responseAsString = response.getResponse().getContentAsString();
        var responseAsArray = TestHelper.parseJsonArray(responseAsString);

        assertThat(responseAsArray.length()).isEqualTo(2);

        var response1 = responseAsArray.getJSONObject(0);
        var response2 = responseAsArray.getJSONObject(1);

        assertThat(response1.get("id").toString()).contains(id1.toString());
        assertThat(response2.get("id").toString()).contains(id2.toString());
    }

}

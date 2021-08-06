package com.aswebsiteapi.image;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class ImageEntityTest {

    private static final String TEST_IMAGE_URL = "test-url";


    @Test
    void shouldCreateImageEntityBuilderWithCorrectValues() {
        var uuid = UUID.randomUUID();
        var createdAt = OffsetDateTime.now();
        var updatedAt = OffsetDateTime.now().plusDays(1L);

        var imageEntityBuilder = ImageEntity.builder().id(uuid).image_url(TEST_IMAGE_URL).createdAt(createdAt).updatedAt(updatedAt);

        assertThat(imageEntityBuilder.getId()).isEqualTo(uuid);
        assertThat(imageEntityBuilder.getImage_url()).isEqualTo(TEST_IMAGE_URL);
        assertThat(imageEntityBuilder.getCreatedAt()).isEqualTo(createdAt);
        assertThat(imageEntityBuilder.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void shouldCreateNewEntityFromBuilder() {
        var uuid = UUID.randomUUID();
        var createdAt = OffsetDateTime.now();
        var updatedAt = OffsetDateTime.now().plusDays(1L);

        var image = ImageEntity.builder().id(uuid).image_url(TEST_IMAGE_URL).createdAt(createdAt).updatedAt(updatedAt)
                .build();

        assertThat(image.getId()).isEqualTo(uuid);
        assertThat(image.getImage_url()).isEqualTo(TEST_IMAGE_URL);
        assertThat(image.getCreatedAt()).isEqualTo(createdAt);
        assertThat(image.getUpdatedAt()).isEqualTo(updatedAt);
    }

}

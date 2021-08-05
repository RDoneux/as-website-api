package com.aswebsiteapi.image;

import com.aswebsiteapi.util.DefaultEntityBuilder;

public class ImageEntityBuilder extends DefaultEntityBuilder<ImageEntityBuilder> {

    private String image_url;

    public ImageEntityBuilder() {
        // EMPTY
    }

    public ImageEntity build() {
        return new ImageEntity(this);
    }

    public ImageEntityBuilder image_url(String image_url) {
        this.image_url = image_url;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

}

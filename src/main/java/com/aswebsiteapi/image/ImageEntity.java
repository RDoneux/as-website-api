package com.aswebsiteapi.image;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aswebsiteapi.util.DefaultEntity;
import com.aswebsiteapi.util.DefaultEntityBuilder;

@Entity
@Table(name = "image")
public class ImageEntity extends DefaultEntity {

    private String image_url;

    public ImageEntity() {
        super(new DefaultEntityBuilder<ImageEntityBuilder>());
    }

    public ImageEntity(ImageEntityBuilder builder) {
        super(builder);
        this.image_url = builder.getImage_url();
    }

    public static ImageEntityBuilder builder() {
        return new ImageEntityBuilder();
    }

    public String getImage_url() {
        return image_url;
    }

}

package com.aswebsiteapi.image;

import java.util.List;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImageController {

    private final ImageRepository imageRepository;
    private final Logger log = Logger.getLogger(this.getClass());

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping(value = "/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public ImageEntity getImage(@PathVariable String imageId) {
        return getEntity(imageId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ImageEntity> getImages() {
        return imageRepository.findAll();
    }

    private ImageEntity getEntity(String uuid) {

        checkIfValidUUID(uuid);

        var maybeImage = imageRepository.findById(UUID.fromString(uuid));
        if (maybeImage.isPresent()) {
            return maybeImage.get();
        } else {
            String errorMessage = "Image with id \"" + uuid + "\" not found";
            log.info(errorMessage);
            throw new ImageControllerEntityNotFoundException(errorMessage);
        }

    }

    private void checkIfValidUUID(String uuid) {
        if (!uuid.matches("^\\{?[A-F0-9a-f]{8}-[A-F0-9a-f]{4}-[A-F0-9a-f]{4}-[A-F0-9a-f]{4}-[A-F0-9a-f]{12}\\}?$")) {
            String errorMessage = uuid + " is not a valid UUID";
            log.info(errorMessage);
            throw new ImageControllerValidationException(errorMessage, "imageId");
        }
    }
}

package school.hei.sary.endpoint.rest.controller.health;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.hei.sary.service.event.ImageService;
import school.hei.sary.service.event.TransformationImage;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/black-and-white/{id}")
    public ResponseEntity<byte[]> getBlackAndWhiteImage(@PathVariable String id) {
        byte[] blackAndWhiteImageBytes = imageService.getBlackAndWhiteImageBytes(id).orElse(null);

        if (blackAndWhiteImageBytes != null) {
            return ResponseEntity.ok().body(blackAndWhiteImageBytes);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/transform/{id}")
    public ResponseEntity<BufferedImage> transformImage(@PathVariable String id) {
        byte[] originalImageBytes = imageService.getBlackAndWhiteImageBytes(id).orElse(null);

        if (originalImageBytes != null) {
            BufferedImage transformedImageBytes = TransformationImage.convertToBlackAndWhite(originalImageBytes);
            if (transformedImageBytes != null) {
                return ResponseEntity.ok().body(transformedImageBytes);
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

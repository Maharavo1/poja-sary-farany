package school.hei.sary.endpoint.rest.controller.health;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school.hei.sary.service.event.ConvertImageService;

@RestController
public class ConvertImageController {
    private final ConvertImageService convertImageService;

    public ConvertImageController(ConvertImageService convertImageService) {
        this.convertImageService = convertImageService;
    }

    @PutMapping(value = "/blacks/{key}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] toBlackAndWhite(@PathVariable String key, @RequestBody byte[] image) {
        return convertImageService.convertToBlackAndWhite(image, key);
    }
}
package school.hei.sary.service.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.sary.PojaGenerated;
import school.hei.sary.file.BucketComponent;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import javax.imageio.ImageIO;

import static org.reflections.Reflections.log;

@PojaGenerated
@Service
@AllArgsConstructor
@Slf4j
public class ImageService {

    @Autowired
    private final BucketComponent bucketComponent;

    public ImageService(BucketComponent bucketComponent) {
        this.bucketComponent = bucketComponent;
    }


    public Optional<byte[]> getBlackAndWhiteImageBytes(String id) {
        try {
            Path originalImagePath = bucketComponent.download(id).toPath();
            byte[] originalImageBytes = Files.readAllBytes(originalImagePath);


            byte[] blackAndWhiteImageBytes = convertToBlackAndWhite(originalImageBytes);

            return Optional.of(blackAndWhiteImageBytes);
        } catch (IOException e) {
            log.error("Error fetching black and white image bytes", e);
            return Optional.empty();
        }
    }

    private byte[] convertToBlackAndWhite(byte[] imageBytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            BufferedImage originalImage = ImageIO.read(bais);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);

            return baos.toByteArray();
        } catch (IOException e) {
            log.error("Error converting image to black and white", e);
            return null;
        }
    }

}

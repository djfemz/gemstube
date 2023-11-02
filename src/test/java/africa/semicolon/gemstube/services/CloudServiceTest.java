package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.exceptions.MediaUploadException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Random;
import static africa.semicolon.gemstube.services.MediaServiceTest.getTestFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class CloudServiceTest {
    @Autowired
    private CloudService cloudService;
    public static final String IMAGE_LOCATION = "C:\\Users\\semicolon\\Documents\\spring_projects\\gemstube\\src\\main\\resources\\assets\\gems-tube-hero-image.jpg";

    public static final String AUDIO_LOCATION = "/home/user/Desktop/GemstubeClone/gemstube/src/main/resources/assets/Ed Sheeran - Castle On The Hill [Official Lyric Video] (2).mp3";
    public static final String VIDEO_LOCATION = "/home/user/Desktop/GemstubeClone/gemstube/src/main/resources/assets/video.mp4";

    public static final String SUBTITLE_FILE = "/home/user/Desktop/GemstubeClone/gemstube/src/main/resources/assets/sub.odt";
    @Test
    public void testUploadImage() throws MediaUploadException, IOException {
        String response = cloudService.upload(getTestFile(IMAGE_LOCATION));
        assertNotNull(response);
    }

    @Test
    public void testUploadVideo() throws MediaUploadException, IOException {
        String response = cloudService.upload(getTestFile(VIDEO_LOCATION));
        System.out.println(response);
        assertNotNull(response);
    }

    @Test
    public void testUploadAudio() throws MediaUploadException, IOException {
        String response = cloudService.upload(getTestFile(AUDIO_LOCATION));
        assertNotNull(response);
    }



}

package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.exceptions.MediaUploadException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static africa.semicolon.gemstube.models.Type.IMAGE;
import static africa.semicolon.gemstube.models.Type.VIDEO;
import static africa.semicolon.gemstube.services.MediaServiceTest.getTestFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class CloudServiceTest {
    @Autowired
    private CloudService cloudService;
    public static final String IMAGE_LOCATION = "C:\\Users\\WEALTHYMAN\\Documents\\REALCODE\\gemstube\\src\\main\\resources\\assets\\gems-tube-hero-image.jpg";

    public static final String AUDIO_LOCATION = "C:\\Users\\WEALTHYMAN\\Documents\\REALCODE\\gemstube\\src\\main\\resources\\assets\\Ed Sheeran - Castle On The Hill [Official Lyric Video].mp3";
    public static final String VIDEO_LOCATION = "C:\\Users\\WEALTHYMAN\\Documents\\REALCODE\\gemstube\\src\\main\\resources\\assets\\Thanos _You could not live with your own failure_ (Avengers End Game Trailer).mp4";
    @Test
    public void testUploadImage() throws MediaUploadException {
        String response = cloudService.upload(getTestFile(IMAGE_LOCATION));
        assertNotNull(response);
    }

    @Test
    public void testUploadVideo() throws MediaUploadException {
        String response = cloudService.upload(getTestFile(VIDEO_LOCATION));
        assertNotNull(response);
    }

    @Test
    public void testUploadAudio() throws MediaUploadException {
        String response = cloudService.upload(getTestFile(AUDIO_LOCATION));
        assertNotNull(response);
    }

//    public static void main(String[] args) {
//        System.out.println(new Random().nextInt(1, 4));
//    }


}

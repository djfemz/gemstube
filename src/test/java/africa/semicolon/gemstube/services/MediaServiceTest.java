package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;

    @Test
    public void testUploadMedia() throws GemsTubeException {
            UploadMediaRequest request = new UploadMediaRequest();
            request.setCreatorId(1L);
            request.setTitle("this is our test media");
            request.setMultipartFile(getTestFile());
            UploadMediaResponse response = mediaService.upload(request);
            assertThat(response).isNotNull();

    }

    public static MultipartFile getTestFile(){
        Path path = Paths.get("C:\\Users\\semicolon\\Documents\\spring_projects\\gemstube\\src\\main\\resources\\assets\\gems-tube-hero-image.jpg");
        try(var inputStream = Files.newInputStream(path);) {
            MultipartFile file = new MockMultipartFile("test-image", inputStream);
            return file;
        }catch (IOException exception){
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}

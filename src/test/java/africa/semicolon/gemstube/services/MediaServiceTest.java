package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static africa.semicolon.gemstube.services.CloudServiceTest.IMAGE_LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserService userService;
    private UploadMediaResponse response;

    @BeforeEach
    public void setUp() throws GemsTubeException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@email.com");
        registerRequest.setPassword("password");
        var registerResponse = userService.register(registerRequest);
        UploadMediaRequest request = new UploadMediaRequest();
        request.setCreatorId(registerResponse.getId());
        request.setTitle("this is our test media");
        request.setMultipartFile(getTestFile(IMAGE_LOCATION));
        response = mediaService.upload(request);
    }

    @Test
    public void testUploadMedia() throws GemsTubeException {
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatWeCanGetMediaById() throws GemsTubeException {
        Media foundMedia = mediaService.getMediaById(response.getMediaId());
        assertNotNull(foundMedia);
    }


    public static MultipartFile getTestFile(String fileLocation){
        Path path = Paths.get(fileLocation);
        try(var inputStream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile("test-image", inputStream);
            return file;
        }catch (IOException exception){
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}

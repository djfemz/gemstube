package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.request.UploadSubtitleRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.dtos.response.UploadSubtitleResponse;
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

import static africa.semicolon.gemstube.services.CloudServiceTest.IMAGE_LOCATION;
import static africa.semicolon.gemstube.services.CloudServiceTest.SUBTITLE_FILE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserService userService;

    @Test
    public void testUploadMedia() throws GemsTubeException, IOException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test1@email.com");
        registerRequest.setPassword("password1");
        var registerResponse = userService.register(registerRequest);
        UploadMediaRequest request = new UploadMediaRequest();
        request.setCreatorId(registerResponse.getId());
        request.setTitle("this is our test media");
        request.setDescription("This is the description");
//        request.setSubtitleFile(Optional.of(getTestFile(SUBTITLE_FILE)));
        request.setMediaFile(getTestFile(IMAGE_LOCATION));
        UploadMediaResponse response = mediaService.upload(request);
        assertThat(response).isNotNull();
    }

    public static MultipartFile getTestFile(String fileLocation){
        Path path = Paths.get(fileLocation);
        try(var inputStream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile(path.getFileName().toString(), inputStream);
            return file;
        }catch (IOException exception){
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }




    @Test
    public void testUploadSubtitle() throws GemsTubeException, IOException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test22@email.com");
        registerRequest.setPassword("p22assword");
        var registerResponse = userService.register(registerRequest);
        UploadMediaRequest request = new UploadMediaRequest();
        request.setCreatorId(registerResponse.getId());
        request.setTitle("this is our test media");
        request.setMediaFile(getTestFile(IMAGE_LOCATION));
//        request.setSubtitleFile(Optional.empty());
        UploadMediaResponse response = mediaService.upload(request);

        UploadSubtitleRequest uploadSubtitleRequest = new UploadSubtitleRequest();
        uploadSubtitleRequest.setMediaId(response.getMediaId());
        uploadSubtitleRequest.setMultipartFile(getTestFile(SUBTITLE_FILE));

        UploadSubtitleResponse uploadSubtitleResponse = mediaService.uploadSubtitle(uploadSubtitleRequest);
        assertThat(uploadSubtitleResponse).isNotNull();
    }
}

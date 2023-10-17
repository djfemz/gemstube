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
import static africa.semicolon.gemstube.services.CloudServiceTest.IMAGE_LOCATION;
import static africa.semicolon.gemstube.services.MediaServiceTest.getTestFile;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SubtitleServiceTest {
    @Autowired
    private SubtitleService subtitleService;
    @Autowired
    private MediaService mediaService;

    @Autowired
    public UserService userService;

    private static final String SUBTITLE_FILE = "/home/user/Desktop/GemstubeClone/gemstube/src/main/resources/assets/sub.odt";

    @Test
    public void testUploadMedia() throws GemsTubeException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test22@email.com");
        registerRequest.setPassword("p22assword");
        var registerResponse = userService.register(registerRequest);
        UploadMediaRequest request = new UploadMediaRequest();
        request.setCreatorId(registerResponse.getId());
        request.setTitle("this is our test media");
        request.setMultipartFile(getTestFile(IMAGE_LOCATION));
        UploadMediaResponse response = mediaService.upload(request);
        assertThat(response).isNotNull();



        UploadSubtitleRequest uploadSubtitleRequest = new UploadSubtitleRequest();
        uploadSubtitleRequest.setMediaId(response.getMediaId());
        uploadSubtitleRequest.setMultipartFile(getTestFile(SUBTITLE_FILE));

        UploadSubtitleResponse uploadSubtitleResponse = subtitleService.upload(uploadSubtitleRequest);
        assertThat(uploadSubtitleResponse).isNotNull();
    }


}

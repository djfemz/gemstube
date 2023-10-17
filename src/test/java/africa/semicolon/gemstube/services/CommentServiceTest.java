package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.CreateCommentRequest;
import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.response.CreateCommentResponse;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static africa.semicolon.gemstube.services.CloudServiceTest.IMAGE_LOCATION;
import static africa.semicolon.gemstube.services.MediaServiceTest.getTestFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    @Test
    public void testCreateComment() throws GemsTubeException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@email.com");
        registerRequest.setPassword("password");
        var registerResponse = userService.register(registerRequest);
        UploadMediaRequest request = new UploadMediaRequest();
        request.setCreatorId(registerResponse.getId());
        request.setTitle("this is our test media");
        request.setMultipartFile(getTestFile(IMAGE_LOCATION));
        UploadMediaResponse response = mediaService.upload(request);

        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setCommenterId(registerResponse.getId());
        createCommentRequest.setMediaId(response.getMediaId());
        createCommentRequest.setMessage("Hey this content is very good");

        CreateCommentResponse createCommentResponse = commentService.createComment(createCommentRequest);
        assertNotNull(createCommentResponse);
        assertNotNull(createCommentResponse.getCommentId());
    }

}

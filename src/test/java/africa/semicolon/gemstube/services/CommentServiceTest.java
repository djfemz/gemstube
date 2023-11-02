package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.AddCommentRequest;
import africa.semicolon.gemstube.dtos.request.UpdateCommentRequest;
import africa.semicolon.gemstube.dtos.response.ApiResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    @Sql("/db/insert.sql")
    public void testAddComment() throws GemsTubeException {
        AddCommentRequest request = new AddCommentRequest();
        request.setCommenter(100L);
        request.setText("I hate your picture");
        var response = commentService.addComment(102L, request);
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(ApiResponse.class);
    }

    @Test
    @Sql({"/db/insert.sql"})
    public void testUpdateComment() throws GemsTubeException {
        UpdateCommentRequest request = new UpdateCommentRequest();
        request.setText("Your picture is now old jare");

        ApiResponse<?> response = commentService.updateComment(200L, 100L, request);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
    }
}

package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.CreateCommentRequest;
import africa.semicolon.gemstube.dtos.response.CreateCommentResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.Comment;
import africa.semicolon.gemstube.models.Media;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Slf4j
public class GemsTubeCommentService implements CommentService{

    private final UserService userService;
    private final MediaService mediaService;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    @Override
    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest) throws GemsTubeException {
        User commenter = userService.getUserById(createCommentRequest.getCommenterId());
        Media mediaToBeCommented = mediaService.getMediaById(createCommentRequest.getMediaId());
        log.info("media --> {}", mediaToBeCommented);
        Comment comment = new Comment();
        comment.setCommenter(commenter);
        comment.setMedia(mediaToBeCommented);
        comment.setMessage(createCommentRequest.getMessage());
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CreateCommentResponse.class);
    }

}

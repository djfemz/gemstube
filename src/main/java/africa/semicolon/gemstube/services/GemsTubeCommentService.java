package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.AddCommentRequest;
import africa.semicolon.gemstube.dtos.request.UpdateCommentRequest;
import africa.semicolon.gemstube.dtos.response.ApiResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.exceptions.ResourceNotFoundException;
import africa.semicolon.gemstube.models.Comment;
import africa.semicolon.gemstube.models.Media;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GemsTubeCommentService implements CommentService{
    private final MediaService mediaService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CommentRepository commentRepository;
    @Override
    public ApiResponse<?> addComment(Long id, AddCommentRequest request) throws GemsTubeException {
        Media foundMedia = mediaService.getMediaById(id);
        Comment comment = modelMapper.map(request, Comment.class);
        comment.setMedia(foundMedia);
        User user =userService.getUserById(request.getCommenter());
        comment.setCommenter(user);
        commentRepository.save(comment);
        ApiResponse<?> response = new ApiResponse<>();
        response.setMessage("Comment added successfully");
        return response;
    }

    @Override
    public ApiResponse<?> updateComment(Long commentId, Long userId, UpdateCommentRequest request) throws GemsTubeException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("Comment with id %d not found",commentId)));

        Long posterId = comment.getCommenter().getId();
        if (!posterId.equals(userId)) throw new GemsTubeException("only original comment poster allowed to update comment");

        comment.setText(request.getText());
        commentRepository.save(comment);

        ApiResponse<?> response =  new ApiResponse<>();
        response.setMessage("Comment updated successfully");
        return response;
    }
}

package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.CreateCommentRequest;
import africa.semicolon.gemstube.dtos.response.CreateCommentResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;

public interface CommentService {

    CreateCommentResponse createComment(CreateCommentRequest createCommentRequest) throws GemsTubeException;
}

package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.AddCommentRequest;
import africa.semicolon.gemstube.dtos.response.ApiResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;


public interface CommentService {
    ApiResponse<?> addComment(Long mediaId, AddCommentRequest request) throws GemsTubeException;
}

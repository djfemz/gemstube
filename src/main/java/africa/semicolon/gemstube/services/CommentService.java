package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.AddCommentRequest;
import africa.semicolon.gemstube.dtos.request.UpdateCommentRequest;
import africa.semicolon.gemstube.dtos.response.ApiResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.exceptions.ResourceNotFoundException;


public interface CommentService {
    ApiResponse<?> addComment(Long mediaId, AddCommentRequest request) throws GemsTubeException;

    ApiResponse<?> updateComment(Long commentId, Long userId, UpdateCommentRequest request) throws GemsTubeException;
}

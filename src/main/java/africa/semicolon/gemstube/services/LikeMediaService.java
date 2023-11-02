package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.LikeMediaRequest;
import africa.semicolon.gemstube.dtos.response.LikeMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;

public interface LikeMediaService {
    LikeMediaResponse updateLikeChoice(LikeMediaRequest likeMediaRequest) throws GemsTubeException;
}

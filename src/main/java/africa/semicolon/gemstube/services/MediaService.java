package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.request.UploadSubtitleRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.dtos.response.UploadSubtitleResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.exceptions.MediaUploadException;
import africa.semicolon.gemstube.models.Media;

import java.io.IOException;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest uploadMediaRequest) throws GemsTubeException, IOException;
    Media getMediaById(Long id) throws GemsTubeException;
    Long countMediaLikes(Long mediaId) throws GemsTubeException;
    Media updateMedia(Media media);
    UploadSubtitleResponse uploadSubtitle (UploadSubtitleRequest request) throws GemsTubeException, IOException;
}

package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.exceptions.MediaNotFoundException;
import africa.semicolon.gemstube.models.Media;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest uploadMediaRequest) throws GemsTubeException;
    Media getMediaById(Long mediaId) throws MediaNotFoundException;
}

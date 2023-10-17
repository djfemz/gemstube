package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.UploadSubtitleRequest;
import africa.semicolon.gemstube.dtos.response.UploadSubtitleResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;

public interface SubtitleService {
    UploadSubtitleResponse upload (UploadSubtitleRequest request) throws GemsTubeException;
}

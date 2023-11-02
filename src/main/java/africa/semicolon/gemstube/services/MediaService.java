package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.request.UploadSubtitleRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.dtos.response.UploadSubtitleResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.exceptions.MediaUploadException;
import africa.semicolon.gemstube.models.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest uploadMediaRequest) throws GemsTubeException, IOException;
    Media getMediaById(Long id) throws GemsTubeException;
    UploadSubtitleResponse uploadSubtitle (UploadSubtitleRequest request) throws GemsTubeException, IOException;

    default UploadMediaRequest buildUploadMediaRequest(MultipartFile media,
                                                        String description,
                                                       String title,
                                                       Long uploaderId){
        UploadMediaRequest uploadMediaRequest = new UploadMediaRequest();
        uploadMediaRequest.setMediaFile(media);
        uploadMediaRequest.setCreatorId(uploaderId);
        uploadMediaRequest.setDescription(description);
        uploadMediaRequest.setTitle(title);
        return uploadMediaRequest;
    }
}

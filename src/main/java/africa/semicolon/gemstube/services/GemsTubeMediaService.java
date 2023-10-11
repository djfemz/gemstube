package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.exceptions.MediaUploadException;
import africa.semicolon.gemstube.models.Media;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class GemsTubeMediaService implements MediaService{
    private final CloudService cloudService;
    private final MediaRepository mediaRepository;
    private final UserService userService;
    @Override
    public UploadMediaResponse upload(UploadMediaRequest uploadMediaRequest) throws GemsTubeException {
        User user = userService.getUserById(uploadMediaRequest.getCreatorId());
        String fileUrl = cloudService.upload(uploadMediaRequest.getMultipartFile());
        Media media = new Media();
        media.setDescription(uploadMediaRequest.getDescription());
        media.setUrl(fileUrl);
        media.setTitle(uploadMediaRequest.getTitle());
        media.setCreatedAt(LocalDateTime.now());
        media.setUploader(user);

        mediaRepository.save(media);
        return null;
    }
}

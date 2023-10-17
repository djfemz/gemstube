package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.UploadSubtitleRequest;
import africa.semicolon.gemstube.dtos.response.UploadMediaResponse;
import africa.semicolon.gemstube.dtos.response.UploadSubtitleResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.Media;
import africa.semicolon.gemstube.models.Subtitle;
import africa.semicolon.gemstube.repositories.SubtitleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;;

@Service
@RequiredArgsConstructor
public class GemsTubeSubtitleService implements SubtitleService{
    private final CloudService cloudService;
    private final SubtitleRepository subtitleRepository;
    private final MediaService mediaService;
    private final ModelMapper modelMapper;


    @Override
    public UploadSubtitleResponse upload(UploadSubtitleRequest request) throws GemsTubeException {
        Media media = mediaService.getMediaById (request.getMediaId());
     String url =   cloudService.upload(request.getMultipartFile());

      Subtitle subtitle =  modelMapper.map(request, Subtitle.class);
        subtitle.setUrl(url);
       subtitle.setMedia(media);
        Subtitle savedSubtitle = subtitleRepository.save(subtitle);
return buildUploadSubtitleResponse(savedSubtitle);

    }

    private static UploadSubtitleResponse buildUploadSubtitleResponse (Subtitle savedSubtitle) {

        UploadSubtitleResponse uploadSubtitleResponse = new UploadSubtitleResponse();
        uploadSubtitleResponse.setMessage("Subtitle upload successful");
        uploadSubtitleResponse.setSubtitleId(savedSubtitle.getId());
        return uploadSubtitleResponse;
    }
}

package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.LikeMediaRequest;
import africa.semicolon.gemstube.dtos.response.LikeMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.Like;
import africa.semicolon.gemstube.models.Media;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.repositories.LikeMediaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RealLikeMediaService implements LikeMediaService{
    private final LikeMediaRepository likeRepository;
    private final UserService userService;
    private final MediaService mediaService;
//    private final ModelMapper modelMapper;

//    @Transactional
    @Override
    public LikeMediaResponse updateLikeChoice(LikeMediaRequest likeMediaRequest) throws GemsTubeException {
        User user = userService.getUserById(likeMediaRequest.getUserId());
        Media media = mediaService.getMediaById(likeMediaRequest.getMediaId());

        Like like = likeRepository.findByUserAndMedia(user, media);
        LikeMediaResponse response = new LikeMediaResponse();

        if (like == null){
            Like newLike = new Like();
            newLike.setMedia(media);
            newLike.setUser(user);
            Like savedLike = likeRepository.save(newLike);
            media.getLikes().add(savedLike);
            response.setId(savedLike.getId());
        }
        else {
            likeRepository.delete(like);
            media.getLikes().remove(like);
        }

        mediaService.updateMedia(media);
        return response;
    }
}

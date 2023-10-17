package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.dtos.request.LikeMediaRequest;
import africa.semicolon.gemstube.dtos.response.LikeMediaResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class LikeMediaTest {
    @Autowired
    private LikeMediaService likeService;

    @Autowired
    private MediaService mediaService;

    @Test
    public void testThatMediaCanBeLiked() throws GemsTubeException {
        LikeMediaRequest likeRequest = new LikeMediaRequest();
        likeRequest.setUserId(1L);
        likeRequest.setMediaId(1L);
        LikeMediaResponse likeResponse = likeService.updateLikeChoice(likeRequest);
        assertThat(likeResponse).isNotNull();
        assertEquals(1L, likeResponse.getId());
        Long countNumber = mediaService.countMediaLikes(1L);
        assertNotNull(countNumber);
        assertThat(countNumber).isEqualTo(1L);
    }
    @Test
    public void testThatMediaCanBeLikedAgain() throws GemsTubeException {
        LikeMediaRequest likeRequest = new LikeMediaRequest();
        likeRequest.setUserId(2L);
        likeRequest.setMediaId(1L);
        LikeMediaResponse likeResponse = likeService.updateLikeChoice(likeRequest);
        assertThat(likeResponse).isNotNull();
        assertEquals(2L, likeResponse.getId());
        Long countNumber = mediaService.countMediaLikes(1L);
        assertNotNull(countNumber);
        assertThat(countNumber).isEqualTo(2L);
    }
    @Test
    public void testThatMediaCanBeUnliked() throws GemsTubeException {
        LikeMediaRequest likeRequest = new LikeMediaRequest();
        likeRequest.setUserId(2L);
        likeRequest.setMediaId(1L);
        LikeMediaResponse likeResponse = likeService.updateLikeChoice(likeRequest);
//        assertThat(likeResponse).isNotNull();
//        assertEquals(2L, likeResponse.getId());
        Long countNumber = mediaService.countMediaLikes(1L);
        assertNotNull(countNumber);
        assertThat(countNumber).isEqualTo(1L);
    }
    @Test
    public void testThatMediaCanBeUnliked1() throws GemsTubeException {
        LikeMediaRequest likeRequest = new LikeMediaRequest();
        likeRequest.setUserId(1L);
        likeRequest.setMediaId(1L);
        LikeMediaResponse likeResponse = likeService.updateLikeChoice(likeRequest);
//        assertThat(likeResponse).isNotNull();
//        assertEquals(2L, likeResponse.getId());
        Long countNumber = mediaService.countMediaLikes(1L);
        assertNotNull(countNumber);
        assertThat(countNumber).isEqualTo(0L);
    }
}
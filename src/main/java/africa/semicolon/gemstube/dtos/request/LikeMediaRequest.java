package africa.semicolon.gemstube.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class LikeMediaRequest {
    private Long userId;
    private Long mediaId;
}

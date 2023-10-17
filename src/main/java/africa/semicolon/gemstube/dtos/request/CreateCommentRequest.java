package africa.semicolon.gemstube.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCommentRequest {
    private Long commenterId;
    private Long mediaId;
    private String message;
}

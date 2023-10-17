package africa.semicolon.gemstube.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    private Long commenterId;
    private Long mediaId;
    private String message;
}

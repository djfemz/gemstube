package africa.semicolon.gemstube.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentResponse {
    private Long commentId;
    private Long mediaId;
}

package africa.semicolon.gemstube.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadSubtitleResponse {
    private String message;
    private Long subtitleId;
}

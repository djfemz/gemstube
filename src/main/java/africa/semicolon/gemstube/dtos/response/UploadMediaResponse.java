package africa.semicolon.gemstube.dtos.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMediaResponse {
    private String message;
    private Long mediaId;
}

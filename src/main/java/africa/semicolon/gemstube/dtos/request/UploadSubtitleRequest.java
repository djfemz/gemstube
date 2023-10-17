package africa.semicolon.gemstube.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadSubtitleRequest {

    private MultipartFile multipartFile;
    private Long mediaId;

}

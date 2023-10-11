package africa.semicolon.gemstube.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadMediaRequest {
    private Long creatorId;
    private String title;
    private MultipartFile multipartFile;
    private String description;
}

package africa.semicolon.gemstube.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadMediaRequest {
    private Long creatorId;
    private String title;
    private MultipartFile mediaFile;
    private String description;
    private Optional<MultipartFile> subtitleFile = Optional.empty();
}

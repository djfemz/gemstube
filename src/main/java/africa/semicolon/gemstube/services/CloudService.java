package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.exceptions.MediaUploadException;
import africa.semicolon.gemstube.models.Type;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile multipartFile) throws MediaUploadException;
}

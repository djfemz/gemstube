package africa.semicolon.gemstube.controllers;

import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.services.MediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/media")
@AllArgsConstructor
@Slf4j
public class MediaController {
    private final MediaService mediaService;

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadMedia(@RequestParam MultipartFile media,
                                            @RequestParam String description,
                                         @RequestParam String title,
                                         @RequestParam("creator") Long id) throws GemsTubeException, IOException {
        UploadMediaRequest request = mediaService.buildUploadMediaRequest(media, description, title, id);
        var response = mediaService.upload(request);
        return ResponseEntity.ok(response);
    }
}

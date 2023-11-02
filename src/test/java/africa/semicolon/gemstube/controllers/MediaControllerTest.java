package africa.semicolon.gemstube.controllers;


import africa.semicolon.gemstube.dtos.request.UploadMediaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static africa.semicolon.gemstube.services.CloudServiceTest.IMAGE_LOCATION;
import static africa.semicolon.gemstube.services.MediaServiceTest.getTestFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MediaControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @Sql({"/db/insert.sql"})
    public void testUploadMedia() throws Exception {
        MultipartFile file = getTestFile(IMAGE_LOCATION);

        Part creator = new MockPart("creator", new byte[]{49,48,48});
        Part title = new MockPart("title","my uploaded file".getBytes());
        Part description = new MockPart("description", "latest file upload".getBytes());

        mockMvc.perform(multipart("/api/v1/media")
                .file(new MockMultipartFile("media", file.getInputStream()))
                .part(creator)
                .part(title)
                .part(description)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}

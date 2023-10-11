package africa.semicolon.gemstube.services;


import africa.semicolon.gemstube.exceptions.MediaUploadException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static africa.semicolon.gemstube.services.MediaServiceTest.getTestFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class CloudServiceTest {
    @Autowired
    private CloudService cloudService;

    @Test
    public void testUploadFile() throws MediaUploadException {
        String response = cloudService.upload(getTestFile());
        assertNotNull(response);
    }
}

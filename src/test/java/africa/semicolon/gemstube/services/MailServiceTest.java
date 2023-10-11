package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.EmailRequest;
import africa.semicolon.gemstube.dtos.request.Recipient;
import africa.semicolon.gemstube.dtos.request.Sender;
import africa.semicolon.gemstube.dtos.response.EmailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendEmail(){
        Recipient recipient = new Recipient();
        recipient.setEmail("wocog82484@mugadget.com");
        recipient.setName("Ambali");
        List<Recipient> recipients = List.of(
                recipient
        );
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setRecipients(recipients);
        emailRequest.setHtmlContent("<p>We are testing our happ</p>");
        emailRequest.setSubject("testing 123...");

        EmailResponse response = mailService.sendMail(emailRequest);
        assertNotNull(response);
        assertNotNull(response.getMessageId());
        assertNotNull(response.getCode());
        assertEquals(201, response.getCode());
    }
}

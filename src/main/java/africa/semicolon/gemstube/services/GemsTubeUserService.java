package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.EmailRequest;
import africa.semicolon.gemstube.dtos.request.Recipient;
import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class GemsTubeUserService implements UserService{
    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        User savedUser = userRepository.save(user);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setRecipients(List.of(new Recipient(savedUser.getEmail(), "Friend")));
        emailRequest.setHtmlContent("<p>Hi, welcome to gemstube.com, we are so...</p>");
        emailRequest.setSubject("Welcome to gemstube streaming service");
        mailService.sendMail(emailRequest);
        return new RegisterResponse(savedUser.getId());
    }
}

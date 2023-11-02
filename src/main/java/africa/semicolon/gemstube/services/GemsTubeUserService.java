package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.EmailRequest;
import africa.semicolon.gemstube.dtos.request.Recipient;
import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import africa.semicolon.gemstube.dtos.response.UserResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class GemsTubeUserService implements UserService{
    private final UserRepository userRepository;
    private final MailService mailService;
    private final ModelMapper modelMapper;

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

    @Override
    public User getUserById(Long id) throws GemsTubeException {
        return userRepository.findById(id).orElseThrow(
                ()-> new GemsTubeException(String.format("user with id %d not found", id))
        );
    }

    @Override
    public UserResponse getUserBy(Long id) throws GemsTubeException {
        User user = getUserById(id);
        return modelMapper.map(user, UserResponse.class);
    }
}

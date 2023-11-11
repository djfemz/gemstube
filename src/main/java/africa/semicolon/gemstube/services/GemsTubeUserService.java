package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.EmailRequest;
import africa.semicolon.gemstube.dtos.request.Recipient;
import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import africa.semicolon.gemstube.dtos.response.UserResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.Authority;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.List;

import static africa.semicolon.gemstube.models.Authority.USER;

@Controller
@AllArgsConstructor
@Slf4j
public class GemsTubeUserService implements UserService{
    private final UserRepository userRepository;
    private final MailService mailService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setAuthorities(List.of(USER));
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

    @Override
    public List<UserResponse> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        log.info("users:: {}", users);
        return users.stream()
                    .map(user->modelMapper.map(user, UserResponse.class))
                    .toList();

    }

    @Override
    public User getUserBy(String email)  {
        return userRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException(
                        String.format("user with email %s not found", email)
                ));
    }
}

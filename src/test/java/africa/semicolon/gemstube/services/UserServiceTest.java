package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;

    @BeforeEach
    public void setUp(){
        registerRequest = new RegisterRequest();
        registerRequest.setEmail("wocog82484@mugadget.com");
        registerRequest.setPassword("password");
        registerResponse = userService.register(registerRequest);
    }

    @Test
    public void registerTest(){
        assertNotNull(registerResponse);
        assertNotNull(registerResponse.getId());
    }

    @Test
    public void testGetUserById() throws GemsTubeException {
        User user = userService.getUserById(registerResponse.getId());
        assertThat(user).isNotNull();
    }
}

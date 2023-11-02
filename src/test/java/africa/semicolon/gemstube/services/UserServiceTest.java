package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import africa.semicolon.gemstube.dtos.response.UserResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Slf4j
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

    @Test
    @Sql("/db/insert.sql")
    public void getUsers(){
        List<UserResponse> users = userService.getUsers(1, 3);
        log.info("users:: {}", users);
        assertThat(users).hasSize(3);

    }
}

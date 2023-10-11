package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void registerTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("wocog82484@mugadget.com");
        registerRequest.setPassword("password");

        RegisterResponse registerResponse = userService.register(registerRequest);
        assertNotNull(registerResponse);
        assertNotNull(registerResponse.getId());
    }
}

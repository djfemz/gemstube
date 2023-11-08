package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import africa.semicolon.gemstube.dtos.response.UserResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);

    User getUserById(Long id) throws GemsTubeException;

    UserResponse getUserBy(Long id) throws GemsTubeException;

    List<UserResponse> getUsers(int page, int size);

    User getUserBy(String username);
}

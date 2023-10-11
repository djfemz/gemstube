package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;
import africa.semicolon.gemstube.exceptions.GemsTubeException;
import africa.semicolon.gemstube.models.User;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);

    User getUserById(Long id) throws GemsTubeException;
}

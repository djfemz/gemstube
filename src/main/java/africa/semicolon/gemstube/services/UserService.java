package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.dtos.request.RegisterRequest;
import africa.semicolon.gemstube.dtos.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);
}

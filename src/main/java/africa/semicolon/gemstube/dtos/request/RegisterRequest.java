package africa.semicolon.gemstube.dtos.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
}

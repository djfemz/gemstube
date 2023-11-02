package africa.semicolon.gemstube.dtos.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddCommentRequest {
    private String text;
    private Long commenter;
}

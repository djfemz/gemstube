package africa.semicolon.gemstube.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

import static jakarta.persistence.GenerationType.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String text;
    @OneToOne
    private User commenter;
    @OneToOne
    private Media media;
}

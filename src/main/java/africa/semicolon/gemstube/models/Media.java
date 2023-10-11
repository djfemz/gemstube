package africa.semicolon.gemstube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String title;
    private String description;
    private String url;
    @OneToOne(fetch = FetchType.EAGER)
    private User uploader;
    private LocalDateTime createdAt;
}

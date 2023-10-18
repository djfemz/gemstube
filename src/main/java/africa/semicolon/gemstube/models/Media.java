package africa.semicolon.gemstube.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Media {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String title;
    private String description;
    private String url;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User uploader;
    private LocalDateTime createdAt;


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}

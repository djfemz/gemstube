package africa.semicolon.gemstube.repositories;


import africa.semicolon.gemstube.models.Subtitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtitleRepository extends JpaRepository<Subtitle, Long> {
}

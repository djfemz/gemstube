package africa.semicolon.gemstube.repositories;

import africa.semicolon.gemstube.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}

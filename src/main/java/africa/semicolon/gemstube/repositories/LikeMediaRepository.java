package africa.semicolon.gemstube.repositories;

import africa.semicolon.gemstube.models.Like;
import africa.semicolon.gemstube.models.Media;
import africa.semicolon.gemstube.models.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LikeMediaRepository extends JpaRepository<Like, Long> {
    Like findByUserAndMedia(User user, Media media);

}

package africa.semicolon.gemstube.repositories;

import africa.semicolon.gemstube.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

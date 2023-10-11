package africa.semicolon.gemstube.repositories;

import africa.semicolon.gemstube.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

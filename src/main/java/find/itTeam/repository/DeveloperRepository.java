package find.itTeam.repository;

import find.itTeam.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
    // todo
    public Optional<DeveloperEntity> findById(Long id);
}

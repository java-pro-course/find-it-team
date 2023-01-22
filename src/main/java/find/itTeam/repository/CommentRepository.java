package find.itTeam.repository;

import find.itTeam.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>{

    @Modifying
    @Query("UPDATE CommentEntity comment SET comment.text = ?1, comment.date = ?2" +
            "WHERE comment.id = ?3")
    void updateById(String text, LocalDate date, Long id);
}

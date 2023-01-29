package find.itTeam.repository;
import find.itTeam.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository <RatingEntity, Long> {
    /**
     * метод для обработки запроса изменения рейтинга
     * @param rating рейтинг
     * @param id его id
     */
    @Modifying
    @Query("UPDATE RatingEntity rating SET rating.rating = ?1" + "WHERE rating.id = ?2")
    void updateById(int rating, Long id);
}

package find.itTeam.repository;

import find.itTeam.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    /**
     * Метод для обработки запроса н6а изменение поста
     * @param content контент (текст и т.п.)
     * @param dateTime дата написания
     * @param postStatus статус поста
     * @param id его id
     */
    @Modifying
    @Query("UPDATE PostEntity post SET post.content = ?1, post.dateTime = ?2, post.postStatus = ?3 " +
            "WHERE post.id = ?4")
    void updateById(String content, LocalDate dateTime, String postStatus, Long id);
}

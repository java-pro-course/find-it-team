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
     * обработка запроса на изменение комментария
     * @param content
     * @param dateTime
     * @param postStatus
     * @param id
     */
    @Modifying
    @Query("UPDATE PostEntity post SET post.content = ?1, post.datetime = ?2, post.post_status = ?3" +
            "WHERE post.id = ?4")
    void updateById(String content, LocalDate dateTime, String postStatus, Long id);
}

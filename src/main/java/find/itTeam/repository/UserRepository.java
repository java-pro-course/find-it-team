package find.itTeam.repository;

import find.itTeam.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    boolean existByEmail(String email);
    UserEntity findByEmail(String email);

    /**
     * Метод для обработки запроса на обновление пользователя
     *
     * @param name Имя пользователя
     * @param surname Фамилия пользователя
     * @param email Эл. почта пользователя
     * @param password Пароль пользователя
     * @param id Id пользователя
     */
    @Modifying
    @Query("UPDATE UserEntity user SET user.name = ?1, user.surname = ?2, user.email = ?3, " +
            "user.password = ?4 WHERE user.id = ?5")
    void updateById(String name, String surname, String email, String password, Long id);
}

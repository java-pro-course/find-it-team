package find.itTeam.repository;

import find.itTeam.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
    DeveloperEntity findByEmail(String email);

    /**
     * Метод для обработки запроса на изменение разработчика
     * @param name имя
     * @param surname фамилия
     * @param email email
     * @param password пароль
     * @param projects проекты (список)
     * @param githubLink ссылка на гитхаб (проект)
     * @param devRole роль разработчика в проекте
     * @param languages языки (программирования)
     * @param developmentArea специализация
     * @param experience опыт (или стаж работы)
     * @param city город
     * @param mainJob основное место работы (есть или нет)
     * @param id ну и id
     */
    @Modifying
    @Query("UPDATE DeveloperEntity updateDev SET updateDev.name = ?1, " +
            "updateDev.surname = ?2, " +
            "updateDev.email = ?3, " +
            "updateDev.password = ?4, " +
            "updateDev.projects = ?5, " +
            "updateDev.githubLink = ?6, " +
            "updateDev.devRole = ?7, " +
            "updateDev.languages = ?8, " +
            "updateDev.developmentArea = ?9, " +
            "updateDev.experience = ?10, " +
            "updateDev.city = ?11, " +
            "updateDev.mainJob = ?12 " +
            "WHERE updateDev.id = ?13")
    void updateDevById(String name,
                       String surname,
                       String email,
                       String password,
                       ArrayList<String> projects,
                       String githubLink,
                       String devRole,
                       ArrayList<String> languages,
                       String developmentArea,
                       String experience,
                       String city,
                       String mainJob,
                       Long id);
}

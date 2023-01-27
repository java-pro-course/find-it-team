package find.itTeam.service;

import find.itTeam.dto.CreateDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.repository.DeveloperRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Data
@Slf4j
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    /**
     * Создание разработчика
     *
     * @param rq данные нового разработчика
     * @return разработчик
     */
    public ResponseEntity<?> createTeamDeveloper(CreateDeveloper rq) {
        //проверка на null
        if (rq.getName() == null || rq.getSurname() == null
                || rq.getEmail() == null || rq.getProjects() == null
                || rq.getGithubLink() == null || rq.getDevRole() == null
                || rq.getLanguages() == null || rq.getDevelopmentArea() == null
                || rq.getExperience() == null || rq.getCity() == null || rq.getMainJob() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ни одно из полей не должно быть пустым!!!");
        }

        DeveloperEntity developer = new DeveloperEntity()
                .setName(rq.getName())
                .setSurname(rq.getSurname())
                .setEmail(rq.getEmail())
                .setPassword(rq.getPassword())
                .setProjects(rq.getProjects())
                .setGithubLink(rq.getGithubLink())
                .setDevRole(rq.getDevRole())
                .setLanguages(rq.getLanguages())
                .setDevelopmentArea(rq.getDevelopmentArea())
                .setExperience(rq.getExperience())
                .setCity(rq.getCity())
                .setMainJob(rq.getMainJob());

        developer = developerRepository.save(developer);
        log.info("All is ok!");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(developer);
    }

    /**
     * Информация о разработчике
     *
     * @return команда разработчиков
     */

    public ResponseEntity<?> infoAboutDeveloperInTeam(Long id) {
        Optional<DeveloperEntity> developer = developerRepository.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(developer);
    }

    public ResponseEntity<?> getAllDev() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(developerRepository.findAll());
    }
}
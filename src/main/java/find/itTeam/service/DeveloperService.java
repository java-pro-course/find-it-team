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
     * @param developers данные нового разработчика
     * @return разработчик
     */
    public ResponseEntity<?> createTeamDeveloper(CreateDeveloper developers) {
        DeveloperEntity developer = new DeveloperEntity();

        //проверка на null
        if (developers.getName() == null || developers.getSurname() == null
                || developers.getEmail() == null || developers.getProjects() == null
                || developers.getGithubLink() == null || developers.getDevRole() == null
                || developers.getLanguages() == null || developers.getDevelopmentArea() == null
                || developers.getExperience() == null || developers.getCity() == null || developers.getMainJob() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        else {
            developer
                    .setName(developers.getName())
                    .setSurname(developers.getSurname())
                    .setEmail(developers.getEmail())
                    .setPassword(developers.getPassword())
                    .setProjects(developers.getProjects())
                    .setGithubLink(developers.getGithubLink())
                    .setDevRole(developers.getDevRole())
                    .setLanguages(developers.getLanguages())
                    .setDevelopmentArea(developers.getDevelopmentArea())
                    .setExperience(developers.getExperience())
                    .setCity(developers.getCity())
                    .setMainJob(developers.getMainJob());
        }

        log.info("All is ok!");
        DeveloperEntity developerSave = developerRepository.save(developer);
        return ResponseEntity.status(200).body(developerSave);
    }

    /**
     * Информация о разработчике
     *
     * @return команда разработчиков
     */

    public ResponseEntity<?> infoAboutDeveloperInTeam(Long id) {
        Optional<DeveloperEntity> developer = developerRepository.findById(id);
        return ResponseEntity.status(200).body(developer.get());
    }
}
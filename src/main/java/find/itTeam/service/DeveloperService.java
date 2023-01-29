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
                || rq.getEmail() == null ||rq.getPassword() == null
                || rq.getProjects() == null || rq.getGithubLink() == null
                || rq.getDevRole() == null || rq.getLanguages() == null
                || rq.getDevelopmentArea() == null || rq.getExperience() == null
                || rq.getCity() == null || rq.getMainJob() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("None of the fields must be null!");
        }
        //Проверки(куча проверок)
        if (!rq.getEmail().contains("@") || !rq.getEmail().contains(".")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email!");
        }
        if (developerRepository.findByEmail(rq.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with that email already exists!");
        }
        //Мы заботимся о безопасности наших разрабов)))
        if (rq.getPassword().toLowerCase().contains(rq.getName().toLowerCase())
                || rq.getPassword().toLowerCase().contains(rq.getSurname().toLowerCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not contains your name or your surname! It's not secure!");
        }
        if (rq.getPassword().equals(rq.getPassword().toLowerCase()) || rq.getPassword().equals(rq.getPassword().toUpperCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain uppercase and lowercase letter!");
        }
        if (!rq.getPassword().contains("0")
                && !rq.getPassword().contains("1")
                && !rq.getPassword().contains("2")
                && !rq.getPassword().contains("3")
                && !rq.getPassword().contains("4")
                && !rq.getPassword().contains("5")
                && !rq.getPassword().contains("6")
                && !rq.getPassword().contains("7")
                && !rq.getPassword().contains("8")
                && !rq.getPassword().contains("9") ){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain numbers!");
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
        if (!developerRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The developer does not exist!");
        }
        Optional<DeveloperEntity> developer = developerRepository.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(developer);
    }

    public ResponseEntity<?> getAllDev() {
        if (developerRepository.findAll().isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No developers yet...");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(developerRepository.findAll());
    }

    public ResponseEntity<?> deleteDevById(Long id) {
        if (!developerRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The developer does not exist");
        }
        developerRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Developer was deleted!");
    }
}
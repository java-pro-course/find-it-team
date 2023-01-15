package find.itTeam.service;

import find.itTeam.dto.CreateDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    /**
     * создание разработчика в команде
     *
     * @param id
     * @return разработчик
     */
    public DeveloperEntity createTeamDeveloper(CreateDeveloper developers) {
        DeveloperEntity developer = new DeveloperEntity();

        //это огромная строчка - проверка на null
        if(developers.getName() == null | developers.getSurname() == null | developers.getEmail() == null | developers.getProjects() == null | developers.getGithubLink() == null | developers.getDevRole() == null | developers.getLanguages() == null | developers.getDevelopmentArea() == null | developers.getExperience() == null | developers.getCity() == null | developers.getMainJob() == null) return null;

        developer.setName(developers.getName());
        developer.setSurname(developers.getSurname());
        developer.setEmail(developers.getEmail());
        developer.setPassword(developers.getPassword());
        developer.setProjects(developers.getProjects());
        developer.setGithubLink(developers.getGithubLink());
        developer.setDevRole(developers.getDevRole());
        developer.setLanguages(developers.getLanguages());
        developer.setDevelopmentArea(developers.getDevelopmentArea());
        developer.setExperience(developers.getExperience());
        developer.setCity(developers.getCity());
        developer.setMainJob(developers.getMainJob());

        System.out.print(developer.getEmail());
        return developerRepository.save(developer);
    }

    /**
     * информация о разработчике в команде
     *
     * @return команда разработчиков
     */

    public DeveloperEntity infoAboutDeveloperInTeam(Long id) {
        Optional<DeveloperEntity> developer = developerRepository.findById(id);
        return developer.get();
    }
}
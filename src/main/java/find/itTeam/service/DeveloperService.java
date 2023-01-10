package find.itTeam.service;

import find.itTeam.dto.CreateTeamDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public DeveloperEntity createTeamDeveloper(CreateTeamDeveloper developers) {
        DeveloperEntity developer = new DeveloperEntity();
        developer.setName(developers.getName());
        developer.setSurname(developers.getSurname());
        developer.setEmail(developers.getEmail());
        developer.setProjects(developers.getProjects());
        developer.setGithubLink(developers.getGithubLink());
        developer.setDevRole(developers.getDevRole());
        developer.setLanguages(developers.getLanguages());
        developer.setDevelopmentArea(developers.getDevelopmentArea());
        developer.setExperience(developers.getExperience());
        developer.setCity(developers.getCity());
        developer.setMainJob(developers.getMainJob());

        return developerRepository.save(developer);
    }

    public DeveloperEntity infoAboutTeamDeveloper(Long id, CreateTeamDeveloper developers) {
        DeveloperEntity developer = developerRepository.findById(id).get();
        developer.setName(developers.getName());
        developer.setSurname(developers.getSurname());
        developer.setEmail(developers.getEmail());
        developer.setProjects(developers.getProjects());
        developer.setGithubLink(developers.getGithubLink());
        developer.setDevRole(developers.getDevRole());
        developer.setLanguages(developers.getLanguages());
        developer.setDevelopmentArea(developers.getDevelopmentArea());
        developer.setExperience(developers.getExperience());
        developer.setCity(developers.getCity());
        developer.setMainJob(developers.getMainJob());
        return developer;
    }
}
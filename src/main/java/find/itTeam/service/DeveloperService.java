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
   public DeveloperEntity createCommandDeveloper(CreateTeamDeveloper developers){
        DeveloperEntity developer = new DeveloperEntity();

        developer.setId(developer.getId());
        developer.setName(developer.getName());
        developer.setSurname(developer.getSurname());
        developer.setEmail(developer.getEmail());
        developer.setProjects(developer.getProjects());
        developer.setGithubLink(developer.getGithubLink());
        developer.setDevRole(developer.getDevRole());
        developer.setLanguages(developer.getLanguages());
       developer.setDevelopmentArea(developer.getDevelopmentArea());
       developer.setExperience(developer.getExperience());
       developer.setCity(developer.getCity());
       developer.setMainJob(developer.getMainJob());

       return developerRepository.save(developer);
    }
}


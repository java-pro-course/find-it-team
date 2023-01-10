package find.itTeam.api;

import find.itTeam.dto.CreateTeamDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.service.DeveloperService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamDeveloperController {
    private final DeveloperService developerService;

    public TeamDeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }
    @PostMapping("create-developer-in-team")
    public DeveloperEntity createTeamDeveloper(@RequestBody CreateTeamDeveloper requestDeveloper){
        return developerService.createTeamDeveloper(requestDeveloper);
    }
    @PostMapping("info-about-developer-in-team")
    public DeveloperEntity infoAboutTeamDeveloper(@PathVariable Long id, @RequestBody CreateTeamDeveloper infoDeveloper){
        return developerService.infoAboutTeamDeveloper(id, infoDeveloper);
    }
}
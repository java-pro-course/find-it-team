package find.itTeam.controller;

import find.itTeam.dto.CreateTeamDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.service.DeveloperService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamDeveloperController {
    private final DeveloperService developerService;

    public TeamDeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }
    @GetMapping("info-about-developer-in-team")
    private DeveloperEntity  createTeamDeveloper(@RequestBody CreateTeamDeveloper requestDeveloper){
        return developerService.createTeamDeveloper(requestDeveloper);
    }
}

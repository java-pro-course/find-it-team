package find.itTeam.api;

import find.itTeam.dto.CreateDeveloper;
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
   public DeveloperEntity createTeamDeveloper(@RequestBody CreateDeveloper requestDeveloper){
        return developerService.createTeamDeveloper(requestDeveloper);
    }
@GetMapping("info-about-developer-in-team/{id}")
    public DeveloperEntity infoAboutDeveloper(@PathVariable Long id, @RequestBody CreateDeveloper dev){
        return developerService.infoAboutDeveloperInTeam(id, dev);
}
}


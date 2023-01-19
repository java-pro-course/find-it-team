package find.itTeam.api;

import find.itTeam.dto.CreateDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.service.DeveloperService;

import org.springframework.web.bind.annotation.*;

@RestController
public class TeamDeveloperController {
    private final DeveloperService developerService;

    public TeamDeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    // todo ResponseEntity<?>
    @PostMapping("create-developer-in-team")
    public DeveloperEntity createTeamDeveloper(@RequestBody CreateDeveloper requestDeveloper) {
        return developerService.createTeamDeveloper(requestDeveloper);
    }

    // todo ResponseEntity<?>
    @GetMapping("get-info-about-developer-in-team/{id}")
    public DeveloperEntity infoAboutDeveloper(@PathVariable Long id) {
        return developerService.infoAboutDeveloperInTeam(id);
    }
}


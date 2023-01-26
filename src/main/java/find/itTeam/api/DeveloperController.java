package find.itTeam.api;

import find.itTeam.dto.CreateDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.service.DeveloperService;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller для программиста
 */
@RestController
@Data
public class DeveloperController {
    private final DeveloperService developerService;

    // todo ResponseEntity<?>
    @PostMapping("create-developer")
    public ResponseEntity<?> createTeamDeveloper(@RequestBody CreateDeveloper requestDeveloper) {
        return developerService.createTeamDeveloper(requestDeveloper);
    }

    // todo ResponseEntity<?>
    @GetMapping("get-developer-info/{id}")
    public ResponseEntity<?> infoAboutDeveloper(@PathVariable Long id) {
        return developerService.infoAboutDeveloperInTeam(id);
    }
}


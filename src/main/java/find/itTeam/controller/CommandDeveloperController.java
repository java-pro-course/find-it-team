package find.itTeam.controller;

import find.itTeam.dto.CreateCommandDeveloper;
import find.itTeam.entity.DeveloperEntity;
import find.itTeam.service.DeveloperService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandDeveloperController {
    private final DeveloperService developerService;

    public CommandDeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }
    @GetMapping("info-about-command-developer")
    private DeveloperEntity createCommandDeveloper(@RequestBody CreateCommandDeveloper requestDeveloper){
        return developerService.createCommandDeveloper(requestDeveloper);
    }
}

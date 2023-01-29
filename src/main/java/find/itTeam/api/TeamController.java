package find.itTeam.api;

import find.itTeam.dto.TeamCommand;
import find.itTeam.service.TeamService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
public class TeamController {
    private final TeamService teamService;

    @PostMapping("create-team")
    public ResponseEntity<?> createTeam(@RequestBody TeamCommand cq){
        return teamService.createTeam(cq);
    }
    @GetMapping("get-all-teams")
    public ResponseEntity<?> getAllTeams(){
        return teamService.getAllTeams();
    }
}

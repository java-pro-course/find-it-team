package find.itTeam.service;

import find.itTeam.dto.TeamCommand;
import find.itTeam.entity.TeamEntity;
import find.itTeam.repository.TeamRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class TeamService {
    private final TeamRepository commandRepository;

    public ResponseEntity<?> createTeam(TeamCommand cq) {
        TeamEntity newCommand = new TeamEntity()
                                        .setTitle(cq.getTitle())
                                        .setDevelopersId(cq.getDevelopers_id());
        commandRepository.save(newCommand);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newCommand);
    }
}

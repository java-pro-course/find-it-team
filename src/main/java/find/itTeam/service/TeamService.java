package find.itTeam.service;

import find.itTeam.dto.TeamCommand;
import find.itTeam.entity.TeamEntity;
import find.itTeam.repository.DeveloperRepository;
import find.itTeam.repository.TeamRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class TeamService {
    private final TeamRepository teamRepository;
    private final DeveloperRepository devRepository;

    public ResponseEntity<?> createTeam(TeamCommand cq) {
        if(cq.getTitle() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The title must not be empty!");
        }
        if (teamRepository.findByTitle(cq.getTitle()) != null){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("A team with that title is already exists!");
        }
        if(cq.getDevelopers_id() == null || cq.getDevelopers_id().size() < 1){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The team must not be empty!");
        }
        for (Long dev_id : cq.getDevelopers_id()){
            if(!devRepository.findById(dev_id).isPresent()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(String.format("A developer with id = %d is not exist!", dev_id));
            }
        }
        TeamEntity newCommand = new TeamEntity()
                                        .setTitle(cq.getTitle())
                                        .setDevelopersId(cq.getDevelopers_id());
        teamRepository.save(newCommand);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newCommand);
    }

    public ResponseEntity<?> getAllTeams() {
        if(teamRepository.findAll().isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No team yet...");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(teamRepository.findAll());
    }

    public ResponseEntity<?> deleteTeam(Long id) {
        if(!teamRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The team is not exist!");
        }
        teamRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("The team was deleted!");
    }
}

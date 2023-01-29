package find.itTeam.dto;

import lombok.Data;
import java.util.ArrayList;

@Data
public class TeamCommand {
    private String title;
    private ArrayList<Long> developers_id;
}

package find.itTeam.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CreateComment {
    private String text;
    private LocalDate dateTime;


}

package find.itTeam.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class ChangeComment {
    private String text;
    private LocalDate dateTime;
}

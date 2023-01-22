package find.itTeam.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePost {
    private String content;
    private LocalDate dateTime;
    private String postStatus;
}
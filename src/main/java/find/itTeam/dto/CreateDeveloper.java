package find.itTeam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateDeveloper {
    private String name;
    private String surname;
    private String email;
    private String password;
    private ArrayList<String> projects;
    private String githubLink;
    private String devRole;
    private ArrayList<String> languages;
    private String developmentArea;
    private String experience;
    private String city;
    private String mainJob;
}
package find.itTeam.dto;


import lombok.Data;

@Data
public class CreateUser {
    private String name;
    private String surname;
    private String email;
    private String password;
}
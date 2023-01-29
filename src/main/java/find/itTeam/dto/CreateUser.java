package find.itTeam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUser {
    private String name;
    private String surname;
    private String email;
    private String password;
}
package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Table(schema = "finditteam", name = "user")
@Data
@Accessors(chain = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
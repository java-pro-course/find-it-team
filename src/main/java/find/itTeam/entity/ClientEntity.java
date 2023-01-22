package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;

/**
 * Entity для заказчика
 */
@Entity
@Table(schema = "finditteam", name = "client")
@Data
@Accessors(chain = true)
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(schema = "finditteam", name = "team")
@Data
@Accessors(chain = true)
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "developers_id")
    private ArrayList<Long> developersId;
}

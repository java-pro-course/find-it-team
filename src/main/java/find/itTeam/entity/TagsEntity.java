package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Entity
@Table(schema = "finditteam_second", name = "tags")
@Data
@Accessors(chain = true)
public class TagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag")
    private String tag;

    @JoinColumn(name = "developer_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private DeveloperEntity developer;
}

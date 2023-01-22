package find.itTeam.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(schema = "finditteam", name = "tags")
@Data
public class TagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tag")
    String tag;

    @JoinColumn(name = "developer_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private DeveloperEntity developer;
}

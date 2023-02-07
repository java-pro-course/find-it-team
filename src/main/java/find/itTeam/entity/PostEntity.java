package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity для поста
 */
@Data
@Entity
@Table(schema = "finditteam_second", name = "post")
@Accessors(chain = true)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "datetime")
    private LocalDate dateTime;

    @Column(name = "status")
    private String postStatus;

    @JoinColumn(name = "author_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity author;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;
}


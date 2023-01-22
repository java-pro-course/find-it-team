package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity для комментария
 */
@Entity
@Table(schema = "finditteam", name = "comment")
@Data
@Accessors(chain = true)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private LocalDate date;

    // todo автор комментария

    // todo (для учеников) создать колонку post_id в таблице comment, сделать foreign_key
    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PostEntity post;
}
package find.itTeam.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(schema = "finditteam", name = "post")
public class PostEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDate dateTime;
    private String postStatus;

    // todo (для учеников) здесь колонку НЕ добавляем, пишем только ответную часть
    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;

}


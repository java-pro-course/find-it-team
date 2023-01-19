package find.itTeam.entity;

import javax.persistence.*;
import java.time.LocalDate;
import lombok.Data;
// скажите че не так потому что у меня не показывает ошибки
@Data
@Entity
@Table(schema = "finditteam", name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private LocalDate dateTime;

    // todo (для учеников) создать колонку post_id в таблице comment, сделать foreign_key
    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PostEntity post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
import javax.persistence.*;
public class PostCommentEntity {
    private String tema;
    private String text;
    private Long id;
// что не так пишите сразу
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String gettema() {
        return tema;
    }
    public void settema(Long tema) {
        this.tema = tema;
    }
    public String gettext() {
        return text;
    }
    public void settext(Long text) {
        this.text = text;
    }
package find.itTeam.dto;

import java.time.LocalDate;

public class CreateComment {
    private Long Id;
    private String text;
    private LocalDate dateTime;
    public CreateComment(Long Id, String text, LocalDate dateTime){
        this.Id = Id;
        this.text = text;
        this.dateTime = dateTime;
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

package find.itTeam.dto;

import java.time.LocalDate;

public class CreateNewPost {

    private String content;
    private LocalDate dateTime;

    public CreateNewPost(String content, LocalDate dateTime){
        this.content = content;
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}

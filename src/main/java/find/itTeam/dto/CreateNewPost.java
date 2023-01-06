package find.itTeam.dto;

import java.time.LocalDate;

public class CreateNewPost {

    private String content;
    private LocalDate dateTime;
    private String postStatus;

    public CreateNewPost(String content, LocalDate dateTime, String postStatus){
        this.content = content;
        this.dateTime = dateTime;
        this.postStatus = "";
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

    public void setPostStatus(String status) {
        this.postStatus = status;
    }

    public String getPostStatus() {
        return postStatus;
    }
}

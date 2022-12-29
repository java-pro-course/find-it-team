import javax.persistence.*;

public class PostEntity {

    private String firstName;
    private String lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
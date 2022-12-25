package find.itTeam.entity;

import javax.persistence.*;

@Entity
@Table(schema = "finditteam", name = "user")
public class UserEntity {

/*
* Советую убрать либо surname, либо lastname. Это одно и тоже!
* Некоторые могут не понять, что это такое.
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname; // =lastname
    // private String lastname; // =surname
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

//    public String getLastname() {
//        return lastname;
//    }

//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

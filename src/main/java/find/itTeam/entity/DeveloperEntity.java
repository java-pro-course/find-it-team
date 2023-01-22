package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity для программиста
 */
@Entity
@Table(schema = "finditteam", name = "developer")
@Data
@Accessors(chain = true)
public class DeveloperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "projects")
    private ArrayList<String> projects;
    @Column(name = "github_link")
    private String githubLink;
    @Column(name = "dev_role")
    private String devRole;
    @Column(name = "languages")
    private ArrayList<String> languages;
    @Column(name = "development_area")
    private String developmentArea;
    @Column(name = "experience")
    private String experience;
    @Column(name = "city")
    private String city;
    @Column(name = "main_job")
    private String mainJob;

    @OneToMany(mappedBy = "developer")
    private List<TagsEntity> tags;
}

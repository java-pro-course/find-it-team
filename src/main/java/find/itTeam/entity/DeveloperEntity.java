package find.itTeam.entity;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Entity для программиста
 */
@Entity
@Table(schema = "finditteam", name = "developer")
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

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getDevRole() {
        return devRole;
    }

    public void setDevRole(String devRole) {
        this.devRole = devRole;
    }

    public ArrayList<String> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<String> projects) {
        this.projects = projects;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public String getDevelopmentArea() {
        return developmentArea;
    }

    public void setDevelopmentArea(String developmentArea) {
        this.developmentArea = developmentArea;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMainJob() {
        return mainJob;
    }

    public void setMainJob(String mainJob) {
        this.mainJob = mainJob;
    }
}

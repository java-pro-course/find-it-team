package find.itTeam.dto;
import find.itTeam.dto.CreateDeveloper;
import java.util.ArrayList;

public class CreateDeveloper {
    private String name;
    private String surname;
    private String email;
    private String password;
    private ArrayList<String> projects;
    private String githubLink;
    private String devRole;
    private ArrayList<String> languages;
    private String developmentArea;
    private String experience;
    private String city;
    private String mainJob;

    public CreateDeveloper(String name, String surname, String email, String password, ArrayList<String> projects, String githubLink,
                               String devRole, ArrayList<String> languages, String developmentArea, String experience, String city, String mainJob) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.projects = projects;
        this.githubLink = githubLink;
        this.devRole = devRole;
        this.languages = languages;
        this.developmentArea = developmentArea;
        this.experience = experience;
        this.city = city;
        this.mainJob = mainJob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname){
        this.surname = surname;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public ArrayList<String> getProjects () {
        return projects;
    }

    public void setProjects (ArrayList < String > projects) {
        this.projects = projects;
    }

    public String getGithubLink () {
        return githubLink;
    }

    public void setGithubLink (String githubLink){
        this.githubLink = githubLink;
    }

    public String getDevRole () {
        return devRole;
    }

    public void setDevRole (String devRole){
        this.devRole = devRole;
    }

    public ArrayList<String> getLanguages () {
        return languages;
    }

    public void setLanguages (ArrayList < String > languages) {
        this.languages = languages;
    }

    public String getDevelopmentArea () {
        return developmentArea;
    }

    public void setDevelopmentArea (String developmentArea){
        this.developmentArea = developmentArea;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city){
        this.city = city;
    }

    public String getMainJob () {
        return mainJob;
    }

    public void setMainJob (String mainJob){
        this.mainJob = mainJob;
    }

    public String getExperience () {
        return experience;
    }

    public void setExperience (String experience){
        this.experience = experience;
    }
}
package find.itTeam.dto;

import find.itTeam.entity.DeveloperEntity;

public class CreateCommandDeveloper {
    private DeveloperEntity developerEntity;
    public CreateCommandDeveloper(DeveloperEntity developerEntity){
        DeveloperEntity developers = new DeveloperEntity();
    }

    public DeveloperEntity getDeveloperEntity() {
        return developerEntity;
    }

    public void setDeveloperEntity(DeveloperEntity developerEntity) {
        this.developerEntity = developerEntity;
    }
}

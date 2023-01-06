package find.itTeam.api;

import find.itTeam.dto.CreateUser;
import find.itTeam.entity.UserEntity;
import find.itTeam.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
    @PostMapping("create-user")
    public UserEntity createUser(@RequestBody CreateUser requestUser){
        return userService.createNewUser(requestUser);
    }

}

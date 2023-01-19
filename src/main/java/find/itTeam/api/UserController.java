package find.itTeam.api;

import find.itTeam.dto.CreateNewUser;
import find.itTeam.entity.PostEntity;
import find.itTeam.entity.UserEntity;
import find.itTeam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // todo ResponseEntity<?>
    @GetMapping("delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    // todo ResponseEntity<?>
    @PostMapping("create-user")
    public UserEntity createUser(@RequestBody CreateNewUser requestUser) {
        return userService.createNewUser(requestUser);
    }

    // todo ResponseEntity<?>
    @PutMapping("update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody CreateNewUser user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("get-user-info/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id){
        return userService.getUserInfo(id);
    }
}

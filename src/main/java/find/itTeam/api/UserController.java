package find.itTeam.api;

import find.itTeam.dto.CreateUser;
import find.itTeam.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller для пользователя
 */
@RestController
@Data
public class UserController {
    private final UserService userService;

    @GetMapping("delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUser requestUser) {
        return userService.createNewUser(requestUser);
    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody CreateUser user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("get-user-info/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id){
        return userService.getUserInfo(id);
    }

    @GetMapping("login")
    public ResponseEntity<?> login(@RequestParam Long id, @RequestParam String email, @RequestParam String pass){
        return userService.login(id, email, pass);
    }
}

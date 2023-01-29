package find.itTeam.service;

import find.itTeam.dto.CreateUser;
import find.itTeam.entity.UserEntity;
import find.itTeam.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Data
public class UserService {
    private final UserRepository userRepository;

    /**
     * Создание пользователя
     *
     * @param user данные нового пользователя
     */
    public ResponseEntity<?> createNewUser(CreateUser user) {
        //Проверки(куча проверок)
        if (user.getName() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The name must not be empty!");
        }
        if (user.getSurname() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The surname must not be empty!");
        }
        if (user.getEmail() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The email must not be empty!");
        }
        if (user.getPassword() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not be empty!");
        }
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("None of the fields must not be empty!");
        }
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email!");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with that email already exists!");
        }
        //Мы заботимся о безопасности наших пользователей)))
        if (user.getPassword().toLowerCase().contains(user.getName().toLowerCase())
                || user.getPassword().toLowerCase().contains(user.getSurname().toLowerCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not contains your name or your surname! It's not secure!");
        }
        if (user.getPassword().equals(user.getPassword().toLowerCase()) || user.getPassword().equals(user.getPassword().toUpperCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain uppercase and lowercase letter!");
        }
        if (!user.getPassword().contains("0")
                && !user.getPassword().contains("1")
                && !user.getPassword().contains("2")
                && !user.getPassword().contains("3")
                && !user.getPassword().contains("4")
                && !user.getPassword().contains("5")
                && !user.getPassword().contains("6")
                && !user.getPassword().contains("7")
                && !user.getPassword().contains("8")
                && !user.getPassword().contains("9") ){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain numbers!");
        }

        UserEntity newUser = new UserEntity()
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());

        UserEntity userSave = userRepository.save(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userSave);
    }

    /**
     * Обновление пользователя по id
     *
     * @param id   ID обновляемого пользователя
     * @param user обновляемый пользователь
     * @return подтверждение действия
     */
    @Transactional
    public ResponseEntity<?> updateUser(Long id, CreateUser user) {
        //Проверки(куча проверок)
        if (user.getName() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The name must not be empty!");
        }
        if (user.getSurname() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The surname must not be empty!");
        }
        if (user.getEmail() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The email must not be empty!");
        }
        if (user.getPassword() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not be empty!");
        }
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("None of the fields must not be empty!");
        }
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email!");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with that email already exists!");
        }
        //Мы заботимся о безопасности наших пользователей)))
        if (user.getPassword().toLowerCase().contains(user.getName().toLowerCase())
                || user.getPassword().toLowerCase().contains(user.getSurname().toLowerCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not contains your name or your surname! It's not secure!");
        }
        if (user.getPassword().equals(user.getPassword().toLowerCase()) || user.getPassword().equals(user.getPassword().toUpperCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain uppercase and lowercase letter!");
        }
        if (!user.getPassword().contains("0")
                && !user.getPassword().contains("1")
                && !user.getPassword().contains("2")
                && !user.getPassword().contains("3")
                && !user.getPassword().contains("4")
                && !user.getPassword().contains("5")
                && !user.getPassword().contains("6")
                && !user.getPassword().contains("7")
                && !user.getPassword().contains("8")
                && !user.getPassword().contains("9") ){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain numbers!");
        }
        userRepository.updateById(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Updated user %s", id));
    }

    /**
     * Удаление пользователя по id
     *
     * @param id ID удаляемого пользователя
     * @return подтверждение действия
     */
    public ResponseEntity<?> deleteUser(Long id) {
        if (!userRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user is not exist!");
        }
        userRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Deleted user %s", id));
    }


    /**
     * Вход пользователя по email и паролю
     *
     * @param email эл. почта пользователя, который входит в свой аккаунт
     * @param pass  пароль, того же пользователя
     * @return подтверждение действия
     */
    public ResponseEntity<?> login(Long id, String email, String pass) {
        UserEntity user = userRepository.findByEmail(email);

        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user is not exist!");
        }
        if (user.getPassword().equals(pass)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successful login! Don't forget your id!");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Password or  is wrong!");
    }

    /**
     * Вывод публичной информации пользователя по id
     *
     * @param id ID пользователя
     * @return информация
     */
    public ResponseEntity<?> getUserInfo(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user is not exist!");
        }

        UserEntity user = userRepository.findById(id).get();

        String result = String.format(
                "Id: %d\n" +
                        "Name: %s\n" +
                        "Surname: %s\n" +
                        "Email: %s",
                id, user.getName(), user.getSurname(), user.getEmail()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}

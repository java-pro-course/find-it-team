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
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");
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
     * @param id ID обновляемого пользователя
     * @param user обновляемый пользователь
     * @return подтверждение действия
     */
    @Transactional
    public ResponseEntity<?> updateUser(Long id, CreateUser user) {
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");
        }
        userRepository.updateById(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("updated user %s", id));
    }

    /**
     * Удаление пользователя по id
     *
     * @param id ID удаляемого пользователя
     * @return подтверждение действия
     */
    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("deleted user %s", id));
    }


    /**
     * Вход пользователя по email и паролю
     *
     * @param email эл. почта пользователя, который входит в свой аккаунт
     * @param pass пароль, того же пользователя
     * @return подтверждение действия
     */
    public ResponseEntity<?> login(String email, String pass) {
        UserEntity user = userRepository.findByEmail(email);

        if (user.getPassword().equals(pass)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successful login!");
        }
        return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");
    }

    /** Вывод публичной информации пользователя по id
     *
     * @param id ID пользователя
     * @return информация
     */
    public ResponseEntity<?> getUserInfo(Long id){
        if(!userRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user is not exist!");
        }

        UserEntity user = userRepository.findById(id).get();

        String result = String.format(
                "Name: %s\n" +
                "Surname: %s\n" +
                "Email: %s",
                user.getName(), user.getSurname(), user.getEmail()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}

package find.itTeam.service;

import find.itTeam.dto.CreateNewUser;
import find.itTeam.entity.UserEntity;
import find.itTeam.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import javax.transaction.Transactional;


@Service
@Data
public class UserService {
    private final UserRepository userRepository;


    /**
     * Создание пользователя
     *
     * @param user
     */
    public ResponseEntity<?> createNewUser(CreateNewUser user) {
        // Проверка того, что все обязательные поля заполнены
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {

            return null;
        } else {
            UserEntity newUser = new UserEntity();

            newUser.setName(user.getName())
                .setSurname(user.getSurname())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
            UserEntity userEn = userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userEn);

        }
    }

    /**
     * Обновление пользователя по id
     *
     * @param id
     * @param user
     * @return подтверждение действия
     */
    @Transactional
    public ResponseEntity<?> updateUser(Long id, CreateNewUser user) {
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");
        } else {
            userRepository.updateById(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(String.format("updated user %s", id));
        }
    }

    /**
     * Удаление пользователя по id
     *
     * @param id
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
     * @param email
     * @param pass
     * @return подтверждение действия
     */
    public String login(String email, String pass) {
        UserEntity user = userRepository.findByEmail(email);

        if (user.getPassword().equals(pass)) {
            return "successfully logined";
        } else {
            return "fail";
        }
    }

    /** Вывод публичной информации пользователя по id
     *
     * @param id
     * @return информация
     */
    public ResponseEntity<?> getUserInfo(Long id){
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

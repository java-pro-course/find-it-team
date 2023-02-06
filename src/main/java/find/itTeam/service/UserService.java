package find.itTeam.service;

import find.itTeam.dto.CreateUser;
import find.itTeam.entity.UserEntity;
import find.itTeam.repository.UserRepository;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static org.springframework.jdbc.support.JdbcUtils.isNumeric;

@Service
@Data
public class UserService {
    private final UserRepository userRepository;

    /**
     * Создание пользователя
     *
     * @param user Данные нового пользователя
     */
    public ResponseEntity<?> createNewUser(CreateUser user) {
        if(bigCheck(user).getBody().toString().contains("!")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bigCheck(user).getBody());
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
     * @param id
     * @param user
     * @return Результат обновления
     */
    @Transactional
    public ResponseEntity<?> updateUser(Long id, CreateUser user) {
        bigCheck(user);

        userRepository.updateById(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Updated user %s", id));
    }

    /**
     * Удаление пользователя по id
     *
     * @param id
     * @return Результат удаления
     */
    public ResponseEntity<?> deleteUser(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
        }

        userRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Deleted user %s", id));
    }


    /**
     * Вход пользователя по email и паролю
     *
     * @param email
     * @param pass
     * @return Результат входа
     */
    public ResponseEntity<?> login(String email, String pass) {
        if (userRepository.findByEmail(email) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user with this email does not exist!");
        }

        if (!userRepository.findByEmail(email).getPassword().equals(pass)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Password or email is wrong!");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successful login!");
    }

    /**
     * Вывод публичной информации пользователя по id
     *
     * @param id ID пользователя
     * @return Информация об пользователе
     */
    public ResponseEntity<?> getUserInfo(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
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

    /**
     * Регистрация пользователя
     *
     * @param name
     * @param surname
     * @param email   (Правила:
     *                1)Должна содержать @(собаку).
     *                2)Должна содержать .(точку)).
     *                3)Не должна содержать пробелы.
     * @param pass    (Правила составления:
     *                1)Должен содержать цифры.
     *                2)Должен содержать специальные символы.
     *                3)Не должен содержать имя или фамилию.
     *                4)Должна быть хоть одна заглавная и строчная буквы.
     *                5)Не должен содержать пробелы.
     * @return Результат
     **/
    public ResponseEntity<?> registration(String name, String surname, String email, String pass) {
        if (userRepository.findByEmail(email) != null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Fail!");
        }

        CreateUser user = new CreateUser()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPassword(pass);

        bigCheck(user);


        createNewUser(user);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createNewUser(user));
    }

    /**
     * Проверка на содержание символов
     *
     * @param current строка для сравнения
     * @param check проверяемая строка
     * @return
     */
    private boolean lettersCheck(String current, String check) {
        for (int i = 0; i < check.length(); i++) {
            for (int j = 0; j < current.length(); j++) {
                String a = String.valueOf(current.charAt(j));
                return check.contains(a);
            }
        }
        return true;
    }

    /**
     * Большой метод для всех основных проверок
     *
     * @param user
     * @return Результат
     */
    private ResponseEntity<?> bigCheck(CreateUser user){
        //Пустота заполнения(null не нужно, т.к. могут быть только пустые строчки).
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("None of the fields must not be empty!");
        }
        //Содержание имени или фамилии в пароле
        if (user.getPassword().toLowerCase().contains(user.getName().toLowerCase())
                || user.getPassword().toLowerCase().contains(user.getSurname().toLowerCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not contain your name or your surname! It's not secure!");
        }
        //Заглавные буквы
        if (user.getPassword().equals(user.getPassword().toLowerCase())
                || user.getPassword().equals(user.getPassword().toUpperCase())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain uppercase and lowercase letters!");
        }

        String symbols = "!#$%&()*+,-./0123456789:;<=>?@[]^_`{|}~";
        //Символы пробелы и т.д.
        if (!lettersCheck(symbols, user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Provide your password with symbols and numbers!");
        }
        //Корректность почты
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email!");
        }
        //Похожий пользователь
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with that email already exists!");
        }
        //Пробелы в почте
        if (user.getEmail().contains(" ")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The email must not contain spaces!");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success");
    }
}



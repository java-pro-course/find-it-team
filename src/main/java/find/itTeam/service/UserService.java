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
                    .body("Fail!");
        }
        UserEntity newUser = new UserEntity()
                                        .setName(user.getName())
                                        .setSurname(user.getSurname())
                                        .setEmail(user.getEmail())
                                        .setPassword(user.getPassword());

        UserEntity userSave = userRepository.save(newUser);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userSave + "");
    }

    /**
     * Обновление пользователя по id
     *
     * @param id
     * @param user
     * @return результат
     */
    @Transactional
    public ResponseEntity<?> updateUser(Long id, CreateUser user) {
        if (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("") || user.getPassword().equals("")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Fail!");
        }
        userRepository.updateById(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Updated user %s", id));
    }

    /**
     * Удаление пользователя по id
     *
     * @param id
     * @return результат
     */
    public ResponseEntity<?> deleteUser(Long id) {
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
     * @return результат
     */
    public ResponseEntity<?> login(Long id, String email, String pass) {
        UserEntity user = userRepository.findByEmail(email);

        if (!userRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user is not exist!");
        }

        if (user.getPassword().equals(pass)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successful login!");
        }

        return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Password or email is wrong!");
    }

    /** Вывод публичной информации пользователя по id
     *
     * @param id
     * @return результат
     */
    public ResponseEntity<?> getUserInfo(Long id){
        if(!userRepository.findById(id).isPresent()){
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

    /**
     * Регистрация пользователя
     *
     * @param name
     * @param surname
     * @param email
     * @param pass
     * @return результат
     */
    public ResponseEntity<?> registration(String name, String surname, String email, String pass){
        if(userRepository.findByEmail(email).getEmail().equals(email)){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Fail!");
        }

        CreateUser user = new CreateUser()
                        .setName(name)
                        .setSurname(surname)
                        .setEmail(email)
                        .setPassword(pass);

        createNewUser(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successful registration!");
    }

    //Не нужно слишком подробно расписывать каждый элемент!
}


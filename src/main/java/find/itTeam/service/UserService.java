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
     * @param user Данные нового пользователя
     */
    public ResponseEntity<?> registration(CreateUser user) {
        //Проверки всех данных на null
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
        //Тут проверка на пробелы и цифры в имени и фамилии
        if (user.getName().contains("0")
                || user.getName().contains(" ")
                || user.getName().contains("1")
                || user.getName().contains("2")
                || user.getName().contains("3")
                || user.getName().contains("4")
                || user.getName().contains("5")
                || user.getName().contains("6")
                || user.getName().contains("7")
                || user.getName().contains("8")
                || user.getName().contains("9") ) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The name must not contain numbers and spaces!");
        }

        String realName = user.getName().trim(); // чтобы не было пользователей "     Alexander    "

        if (user.getSurname().contains("0")
                || user.getSurname().contains(" ")
                || user.getSurname().contains("1")
                || user.getSurname().contains("2")
                || user.getSurname().contains("3")
                || user.getSurname().contains("4")
                || user.getSurname().contains("5")
                || user.getSurname().contains("6")
                || user.getSurname().contains("7")
                || user.getSurname().contains("8")
                || user.getSurname().contains("9") ) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The surname must not contain numbers and spaces!");
        }
        //Проверка email на содержание пробелов
        if (user.getEmail().contains(" ")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The email must not contains space!");
        }
        //Проверка email на содержание @(Почтовой собаки) и .(точки)
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email!");
        }
        //Проверка, не используется ли этот email у другого пользователя
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with that email already exists!");
        }
        //Мы заботимся о безопасности наших пользователей)))
        //Проверка пароля на содержание пробелов
        if (user.getPassword().contains(" ")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not contains space!");
        }
        //Проверка пароля на содержание в нём имени или фамилии пользователя
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
        //Проверка пароля на содержание цифр(0-9)
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
        //Проверка пароля на содержание специальных символов
        if (!user.getPassword().contains("-")
                && !user.getPassword().contains("+")
                && !user.getPassword().contains("_")
                && !user.getPassword().contains("=")
                && !user.getPassword().contains("/")
                && !user.getPassword().contains("*")
                && !user.getPassword().contains(")")
                && !user.getPassword().contains("(")
                && !user.getPassword().contains("\\")
                && !user.getPassword().contains(">")
                && !user.getPassword().contains("<")
                && !user.getPassword().contains(",")
                && !user.getPassword().contains(".")
                && !user.getPassword().contains("?")
                && !user.getPassword().contains(";")
                && !user.getPassword().contains(":")
                && !user.getPassword().contains("\"")
                && !user.getPassword().contains("'")
                && !user.getPassword().contains("!")
                && !user.getPassword().contains("@")
                && !user.getPassword().contains("#")
                && !user.getPassword().contains("№")
                && !user.getPassword().contains("$")
                && !user.getPassword().contains("%")
                && !user.getPassword().contains("^")
                && !user.getPassword().contains("&")
                && !user.getPassword().contains("{")
                && !user.getPassword().contains("}")
                && !user.getPassword().contains("[")
                && !user.getPassword().contains("]")
                && !user.getPassword().contains("~")
                && !user.getPassword().contains("`")){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain special characters!");
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
     * @param user Обновляемый пользователь
     * @return Успешное/Неуспешное обновление
     */
    @Transactional
    public ResponseEntity<?> updateUser(Long id, CreateUser user) {
        //Проверки всех данных на null
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
        //Тут проверка на пробелы и цифры в имени и фамилии
        if (!user.getName().contains("0")
                && !user.getName().contains(" ")
                && !user.getName().contains("1")
                && !user.getName().contains("2")
                && !user.getName().contains("3")
                && !user.getName().contains("4")
                && !user.getName().contains("5")
                && !user.getName().contains("6")
                && !user.getName().contains("7")
                && !user.getName().contains("8")
                && !user.getName().contains("9") ) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The name must not contain numbers and spaces!");
        }
        if (!user.getSurname().contains("0")
                && !user.getSurname().contains(" ")
                && !user.getSurname().contains("1")
                && !user.getSurname().contains("2")
                && !user.getSurname().contains("3")
                && !user.getSurname().contains("4")
                && !user.getSurname().contains("5")
                && !user.getSurname().contains("6")
                && !user.getSurname().contains("7")
                && !user.getSurname().contains("8")
                && !user.getSurname().contains("9") ) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The surname must not contain numbers and spaces!");
        }
        //Проверка email на содержание пробелов
        if (user.getEmail().contains(" ")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The email must not contains space!");
        }
        //Проверка email на содержание @(Почтовой собаки) и .(точки)
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email!");
        }
        //Проверка, не используется ли этот email у другого пользователя
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with that email already exists!");
        }
        //Мы заботимся о безопасности наших пользователей)))
        //Проверка пароля на содержание пробелов
        if (user.getPassword().contains(" ")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must not contains space!");
        }
        //Проверка пароля на содержание в нём имени или фамилии пользователя
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
        //Проверка пароля на содержание цифр(0-9)
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
        //Проверка пароля на содержание специальных символов
        if (!user.getPassword().contains("-")
                && !user.getPassword().contains("+")
                && !user.getPassword().contains("_")
                && !user.getPassword().contains("=")
                && !user.getPassword().contains("/")
                && !user.getPassword().contains("*")
                && !user.getPassword().contains(")")
                && !user.getPassword().contains("(")
                && !user.getPassword().contains("\\")
                && !user.getPassword().contains(">")
                && !user.getPassword().contains("<")
                && !user.getPassword().contains(",")
                && !user.getPassword().contains(".")
                && !user.getPassword().contains("?")
                && !user.getPassword().contains(";")
                && !user.getPassword().contains(":")
                && !user.getPassword().contains("\"")
                && !user.getPassword().contains("'")
                && !user.getPassword().contains("!")
                && !user.getPassword().contains("@")
                && !user.getPassword().contains("#")
                && !user.getPassword().contains("№")
                && !user.getPassword().contains("$")
                && !user.getPassword().contains("%")
                && !user.getPassword().contains("^")
                && !user.getPassword().contains("&")
                && !user.getPassword().contains("{")
                && !user.getPassword().contains("}")
                && !user.getPassword().contains("[")
                && !user.getPassword().contains("]")
                && !user.getPassword().contains("~")
                && !user.getPassword().contains("`")){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password must contain special characters!");
        }
        //Обновление пользователя
        userRepository.updateById(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), id);

        //Пользователь (id) обновлён
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
        //Проверка на существование пользователя с таким id
        if (!userRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
        }
        //Удаление пользователя из бд
        userRepository.deleteById(id);

        //Пользователь (id) удалён
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Deleted user %s", id));
    }


    /**
     * Вход пользователя по email и паролю
     *
     * @param email Эл. почта пользователя, который входит в свой аккаунт
     * @param pass  Пароль, того же пользователя
     * @return Успешный/Неуспешный вход
     */
    public ResponseEntity<?> login(Long id, String email, String pass) {
        //Проверка на существование пользователя с таким id
        if (!userRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user with that id does not exist!");
        }
        //Проверка на существование пользователя с таким email
        if (userRepository.findByEmail(email) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user with that email does not exist!");
        }
        //Создание UserEntity
        UserEntity user = userRepository.findById(id).get();
        //Проверка правильности пароля
        if (!user.getPassword().equals(pass)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Password or email is wrong!");
        }
        //Успешный логин
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successful login! Don't forget your id!");
    }

    /**
     * Вывод публичной информации пользователя по id
     *
     * @param id ID пользователя
     * @return Информация об пользователе
     */
    public ResponseEntity<?> getUserInfo(Long id) {
        //Проверка на существование пользователя с таким id
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
        }

        UserEntity user = userRepository.findById(id).get();

        //Формирование результата
        String result = String.format(
                "Id: %d\n" +
                "Name: %s\n" +
                "Surname: %s\n" +
                "Email: %s",
                id, user.getName(), user.getSurname(), user.getEmail()
        );
        //Вывод результата
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /**
      Регистрация пользователя
      @param name Имя пользователя
     * @param surname Фамилия пользователя
     * @param email Эл. почта пользователя (Правила:
     *              1)Должна содержать @(почтовую собаку).
     *              2)Должна содержать .(точку))
     *              3)Не должна содержать пробелы.
     * @param pass Пароль пользователя (Правила составления:
     *             1)Должен содержать хотя бы одну цифру.
     *             2)Должен содержать хотя бы один специальный символ.
     *             3)Не должен содержать имя или фамилию.
     *             4)Должна быть хоть одна заглавная и строчная буквы)
     *             5)Не должен содержать пробелы.
     * @return Результат
     **/
//    public ResponseEntity<?> registration(String name, String surname, String email, String pass){
////        if(userRepository.findByEmail(email).getEmail().equals(email)){
////            return ResponseEntity
////                    .status(HttpStatus.NOT_FOUND)
////                    .body("Fail!");
////        }
//
//        CreateUser user = new CreateUser()
//                        .setName(name)
//                        .setSurname(surname)
//                        .setEmail(email)
//                        .setPassword(pass);
//
//        //Создание нового пользователя
//        createNewUser(user);
//
//        //Успешная регистрация!
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(createNewUser(user));
//    }
//
//    //Не нужно слишком подробно расписывать каждый элемент!
}
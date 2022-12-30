package find.itTeam.service;

import find.itTeam.dto.CreateUser;
import find.itTeam.entity.UserEntity;
import find.itTeam.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Создание пользователя
     * @param user
     */
    public UserEntity createNewUser(CreateUser user){
        UserEntity newUser = new UserEntity();

        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return userRepository.save(newUser);
    }

    /**
     * Обновление пользователя по id
     * @param id
     * @param user
     */
    public UserEntity updateUser(Long id, UserEntity user){
        UserEntity updUser = userRepository.findById(id).get();

        updUser.setName(user.getName());
        updUser.setSurname(user.getSurname());
        updUser.setEmail(user.getEmail());
        updUser.setPassword(user.getPassword());

        return userRepository.save(updUser);
    }

    /**
     * Удаление пользователя по id
     * @param id
     * @return подтверждение действия
     */
    public String deleteUser(Long id){
        userRepository.deleteById(id);

        return "deleted!";
    }

    /**
     * Вход пользователя по email и паролю
     * @param email
     * @param pass
     * @return подтверждение действия
     */
    public String login(String email, String pass){
        UserEntity user = userRepository.findByEmail(email);

        if(user.getPassword() == pass){
            return "success";
        }else{
            return "fail";
        }
    }

}

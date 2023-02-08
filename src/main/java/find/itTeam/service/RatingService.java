package find.itTeam.service;

import find.itTeam.entity.DeveloperEntity;
import find.itTeam.entity.RatingEntity;
import find.itTeam.entity.UserEntity;
import find.itTeam.repository.DeveloperRepository;
import find.itTeam.repository.RatingRepository;
import find.itTeam.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final DeveloperRepository developerRepository;

    /**
     * Создание рейтинга
     * @param rating Dto для рейтинга
     * @param userId Id пользователя
     * @param devId Id разработчика
     * @return Успешное/Неуспешное оценивание
     */
    public ResponseEntity<?> addRating(Long userId, int rating, Long devId){
        Optional<UserEntity> user = userRepository.findById(userId);
        //проверка на существование пользователя (на всякий)
        if (!user.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The user does not exist!");
        }
        Optional<DeveloperEntity> dev = developerRepository.findById(devId);
        //Проверка на существование разработчика
        if (!dev.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("You don't exist!😁");
            }
        if (rating < 1 || rating > 5){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Rating = 1-5. Not 0, not 6, not 999!");
        }
        RatingEntity newRating = new RatingEntity()
                .setUser(user.get())
                .setDeveloper(dev.get())
                .setRating(rating);

        RatingEntity ratingSave = ratingRepository.save(newRating);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ratingSave);
    }

    /**
     * Изменение рейтинга по id
     * @param id Id рейтинга
     * @param rating Dto для рейтинга
     * @return Подтверждение об обновлении
     */
    public ResponseEntity<?> updateRating(Long id, int rating){
       Optional<RatingEntity> ratingEntity = ratingRepository.findById(id);
       if (!ratingEntity.isPresent()){
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(String.format("Rating %d doesn't exist...", id));
       }
        if (rating < 1 || rating > 5){
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body("Rating = 1-5. Not 0, not 6, not 999!");
       }
       ratingRepository.updateById(rating, id);

       return ResponseEntity
               .status(HttpStatus.OK)
               .body(String.format("Updated rating %d", id));
    }

    public ResponseEntity<?> deleteRating(Long id) {
        if (!ratingRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format("Rating %d doesn't exist...", id));
        }
        ratingRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Deleted rating %d", id));
    }
}
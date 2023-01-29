package find.itTeam.service;

import find.itTeam.dto.Rating;
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
     * @param rating dto для рейтинга
     * @param userId id пользователя
     * @param devId id разработчика
     * @return
     */
    public ResponseEntity<?> Rating(Rating rating, Long userId, Long devId){
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Fail");
        }
        Optional<DeveloperEntity> dev = developerRepository.findById(devId);
            if (!dev.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Fail");
            }
        if (rating.getRating() == 0){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Fail");
        }
        RatingEntity newRating = new RatingEntity()
                .setUser(user.get())
                .setDeveloper(dev.get())
                .setRating(rating.getRating());

        RatingEntity ratingSave = ratingRepository.save(newRating);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ratingSave);
    }

    /**
     * Изменение рейтинга по id
     * @param id id рейтинга
     * @param rating dto для рейтинга
     * @return поддтверждение об обновлении
     */
    public ResponseEntity<?> updateRating(Long id, Rating rating){
       Optional<RatingEntity> ratingEntity = ratingRepository.findById(id);

       if (!ratingEntity.isPresent()){
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(String.format("Rating %s doesn't exist...", id));
       }
        if (rating.getRating() == 0){
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body("Fail");
       }
       ratingRepository.updateById(rating.getRating(), rating.getId());

       return ResponseEntity
               .status(HttpStatus.OK)
               .body(String.format("Update rating %s", id));
    }
}

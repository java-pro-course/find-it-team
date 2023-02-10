package find.itTeam.api;

import find.itTeam.service.RatingService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller для рейтинга
 */
@RestController
@Data
public class RatingController {
//    private final RatingService ratingService;
//
//    @GetMapping("add-rating/{userId}/{devId}/{rating}")
//    public ResponseEntity<?> addRating(@PathVariable Long userId, int rating, Long devId){
//        return ratingService.addRating(userId, rating, devId);
//    }
//    @GetMapping("update-rating/{id}")
//    public ResponseEntity<?> updateRating(@PathVariable Long id, int rating){
//        return ratingService.updateRating(id, rating);
//
//    }
//    @GetMapping("delete-rating/{id}")
//    public ResponseEntity<?> deleteRating(@PathVariable Long id){
//        return ratingService.deleteRating(id);
//    }
}

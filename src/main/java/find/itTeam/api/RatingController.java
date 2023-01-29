package find.itTeam.api;

import find.itTeam.dto.Rating;
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
    private final RatingService ratingService;

    @PostMapping("add-rating/{userId}/{devId}")
    public ResponseEntity<?> addRating(Long userId, @RequestBody Rating rating, Long devId){
        return ratingService.addRating(rating, userId, devId);
    }
    @GetMapping("update-rating/{id}")
    public ResponseEntity<?> updateRating(@PathVariable Long id, @RequestBody Rating rating){
        return ratingService.updateRating(id, rating);

    }
}

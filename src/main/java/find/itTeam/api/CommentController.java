package find.itTeam.api;

import find.itTeam.dto.CreateComment;
import find.itTeam.entity.CommentEntity;
import find.itTeam.service.CommentService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller для комментария
 */
@RestController
@Data
public class CommentController {
    private final CommentService commentService;

    @PostMapping("create-comment/{postId}")
    public ResponseEntity<?> createComment(@RequestBody CreateComment requestComment, @PathVariable Long postId) {
        return commentService.createNewComment(requestComment, postId);
    }

    @GetMapping("update-comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CreateComment comment) {
        return commentService.updateComment(id, comment);
    }

    @GetMapping("delete-comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
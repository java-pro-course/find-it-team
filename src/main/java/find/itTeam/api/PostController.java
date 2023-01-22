package find.itTeam.api;

import find.itTeam.dto.CreatePost;
import find.itTeam.service.PostService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller для поста
 */
@RestController
@Data
public class PostController {
    private final PostService postService;

    @GetMapping("delete-post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @PostMapping("create-post")
    public ResponseEntity<?> createNewPost(@RequestBody CreatePost requestPost) {
        return postService.createNewPost(requestPost);
    }

    @PutMapping("update-post/{id}")
    public ResponseEntity<?> updatePost(@RequestBody CreatePost post, @PathVariable Long id) {
        return postService.updatePost(post, id);
    }

}
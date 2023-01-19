package find.itTeam.api;

import find.itTeam.dto.CreateNewPost;
import find.itTeam.entity.PostEntity;
import find.itTeam.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("delete-post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    // todo ResponseEntity<?>
    @PostMapping("create-post")
    public ResponseEntity<?> createNewPost(@RequestBody CreateNewPost requestPost) {
        return postService.createNewPost(requestPost);
    }

    @PutMapping("update-post/{id}")
    public ResponseEntity<?> updatePost(@RequestBody CreateNewPost post,
                                 @PathVariable Long id) {
        return postService.updatePost(post, id);
    }

}
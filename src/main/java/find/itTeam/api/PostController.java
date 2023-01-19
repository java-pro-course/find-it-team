package find.itTeam.api;

import find.itTeam.dto.CreateNewPost;
import find.itTeam.entity.PostEntity;
import find.itTeam.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // todo ResponseEntity<?>
    @GetMapping("delete-post/{id}")
    public String deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    // todo ResponseEntity<?>
    @PostMapping("create-post")
    public PostEntity createNewPost(@RequestBody CreateNewPost requestPost) {
        return postService.createNewPost(requestPost);
    }

    // todo ResponseEntity<?>
    @PutMapping("update-post/{id}")
    public PostEntity updatePost(@RequestBody CreateNewPost post,
                                 @PathVariable Long id) {
        return postService.updatePost(post, id);
    }

}
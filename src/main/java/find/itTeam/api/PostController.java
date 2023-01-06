package find.itTeam.api;

import find.itTeam.dto.CreateNewPost;
import find.itTeam.entity.PostEntity;
import find.itTeam.service.PostService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("delete-post/{id}")
    public String deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @PostMapping("create-post")
    public PostEntity createNewPost(@RequestBody CreateNewPost requestPost) {
        return postService.createNewPost(requestPost);
    }

    @GetMapping("update-post/{id}/{content}/{dateTime}")
    public PostEntity updatePost (@RequestBody PostEntity requestPost){
        return postService.updatePost(requestPost);
    }
}
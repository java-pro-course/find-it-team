package find.itTeam.api;

import find.itTeam.dto.CreateTag;
import find.itTeam.service.TagsService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
public class TagController {
    private final TagsService tagsService;
    @GetMapping("delete-tag/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return tagsService.deleteTag(id);
    }
    @PostMapping("create-tag")
    public ResponseEntity<?> createTag(@RequestBody CreateTag rq){
        return tagsService.createNewTag(rq);
    }
}

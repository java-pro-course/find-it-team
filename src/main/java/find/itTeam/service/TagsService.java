package find.itTeam.service;

import find.itTeam.dto.CreateTag;
import find.itTeam.entity.TagsEntity;
import find.itTeam.repository.TagsRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class TagsService {
    final private TagsRepository tagsRepository;

    public ResponseEntity<?> createNewTag(CreateTag tag){
        if (tag.getTag() == "") {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("failed");
        } else {
            TagsEntity newTag = new TagsEntity()
                    .setTag(tag.getTag());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(newTag);
        }
    }
    public ResponseEntity<?> deleteTag(Long tagId){
        tagsRepository.deleteById(tagId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("deleted tag %s", tagId));
    }
}

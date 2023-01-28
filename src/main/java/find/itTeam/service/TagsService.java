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
        if (tag.getTag() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The tag must not be equals null!");
        }
        TagsEntity newTag = new TagsEntity()
                    .setTag(tag.getTag());
        tagsRepository.save(newTag);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(newTag);
    }
    public ResponseEntity<?> deleteTag(Long tagId){
        if(!tagsRepository.findById(tagId).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The tag is not exist!");
        }

        tagsRepository.deleteById(tagId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Deleted tag %d", tagId));
    }
}

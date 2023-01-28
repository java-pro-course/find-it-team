package find.itTeam.service;

import find.itTeam.dto.CreateComment;
import find.itTeam.entity.CommentEntity;
import find.itTeam.entity.PostEntity;
import find.itTeam.repository.CommentRepository;
import find.itTeam.repository.PostRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Data
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * Создание нового комментария
     *
     * @param comment dto для создания комментария
     * @param postId id поста, под которым создаётся комментарий
     * @return в
     */
    public ResponseEntity<?> createNewComment(CreateComment comment, Long postId) {
        Optional<PostEntity> post = postRepository.findById(postId);

        if (!post.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Post doesn't exist!");
        }
        if (comment.getText().equals("") || comment.getDate() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Fail");
        }
        CommentEntity newComment = new CommentEntity()
                    .setText(comment.getText())
                    .setDate(comment.getDate())
                    .setPost(post.get());

        CommentEntity commentSave = commentRepository.save(newComment);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentSave);
    }

    /**
     * Изменение комментария по id
     *
     * @param id id обновляемого комментария
     * @param comment dto для создания комментария
     * @return  Строка "Update comment id-комментария"
     */
    public ResponseEntity<?> updateComment(Long id, CreateComment comment) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);

        if (!commentEntity.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format("Comment %s doesn't exist!", id));
        }

        if (comment.getText().equals("") || comment.getDate() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");
        }
        commentRepository.updateById(comment.getText(), comment.getDate(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Updated comment %s", id));
    }
    /**
     * Удаление комментария по id
     * @param id идентификатор удаляемого комментария
     * @return строка "Delete comment id-комментария"
     */
    public ResponseEntity<?> deleteComment(Long id){
        commentRepository.deleteById(id);

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(String.format("Deleted comment %s", id));
    }
}

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
     * @param comment
     * @param postId
     * @return
     */
    public ResponseEntity<?> createNewComment(CreateComment comment, Long postId) {
        Optional<PostEntity> post = postRepository.findById(postId);

        if (!post.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Post doesn't exist!");
        }
        if (comment.getText().equals("") || comment.getDateTime() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");
        } else {

            CommentEntity newComment = new CommentEntity()
                    .setText(comment.getText())
                    .setDateTime(comment.getDateTime())
                    .setPost(post.get());

            CommentEntity commentSave = commentRepository.save(newComment);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(commentSave);
        }
    }

    /**
     * Изменение комментария по id
     *
     * @param id
     * @param comment
     */
    public ResponseEntity<?> updateComment(Long id, CreateComment comment) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);

        if (!commentEntity.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format("comment %s doesn't exist!", id));
        }
        if (comment.getText().equals("") || comment.getDateTime() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");
        } else {
            // todo (для учеников) сделать метод в репозитории для обновления
            commentEntity.setText(comment.getText())
                    .setDateTime(comment.getDateTime());
            CommentEntity newComment = commentRepository.save(commentEntity);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(newComment);
        }
    }

        /**
         * Удаление комментария по id
         * @param id
         * @return результат
         */
        public ResponseEntity<?> deleteComment(Long id){
            commentRepository.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(String.format("deleted comment %s", id));
        }
    }
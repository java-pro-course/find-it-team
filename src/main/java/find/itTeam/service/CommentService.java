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
import java.time.LocalDate;
import java.util.Optional;

@Service
@Data
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * Создание нового комментария
     *
     * @param comment Dto для создания комментария
     * @param postId Id поста, под которым создаётся комментарий
     * @return Успешное/Неуспешное создание комментария
     */
    public ResponseEntity<?> createNewComment(CreateComment comment, Long postId) {
        Optional<PostEntity> post = postRepository.findById(postId);
        //проверка на существование поста, под который добавляют комментарий
        if (!post.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Post doesn't exist!");
        }
        if (comment.getText().equals("")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Fail");
        }
        CommentEntity newComment = new CommentEntity()
                    .setText(comment.getText())
                    .setDate(LocalDate.now())
                    .setPost(post.get());

        CommentEntity commentSave = commentRepository.save(newComment);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentSave);
    }

    /**
     * Изменение комментария по id
     *
     * @param id Id обновляемого комментария
     * @param comment Dto для создания комментария
     * @return  Строка "Update comment id-комментария"
     */
    public ResponseEntity<?> updateComment(Long id, CreateComment comment) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);

        if (!commentEntity.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format("Comment %d doesn't exist!", id));
        }

        if (comment.getText().equals("")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Fail");
        }
        commentRepository.updateById(comment.getText(), LocalDate.now(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Updated comment %d", id));
    }
    /**
     * Удаление комментария по id
     * @param id Id удаляемого комментария
     * @return Строка "Delete comment id-комментария"
     */
    public ResponseEntity<?> deleteComment(Long id){
        if (!commentRepository.findById(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The post does not exist!");
        }
        commentRepository.deleteById(id);

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(String.format("Deleted comment %s", id));
    }
}

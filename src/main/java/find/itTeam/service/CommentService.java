package find.itTeam.service;

import find.itTeam.dto.CreateComment;
import find.itTeam.entity.CommentEntity;
import find.itTeam.entity.PostEntity;
import find.itTeam.repository.CommentRepository;
import find.itTeam.repository.PostRepository;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentEntity createNewComment(CreateComment comment, Long postId) {
        // todo (для учеников) проверка на обязательные поля

        Optional<PostEntity> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            return null;
        }
        if(comment.getText() == null | comment.getDateTime() == null) return null;

        CommentEntity newComment = new CommentEntity();
        newComment.setText(comment.getText());
        newComment.setDateTime(comment.getDateTime());
        newComment.setPost(post.get());
        return commentRepository.save(newComment);
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
                    .status(HttpStatus.NOT_FOUND)
                    .body(String.format("Comment with id = %s is not found.", id));
        }

        if (comment.getText() == null | comment.getDateTime() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Text and date-time fields are null.");
        }
        // todo (для учеников) сделать метод в репозитории для обновления
        commentEntity.get().setText(comment.getText());
        commentEntity.get().setDateTime(comment.getDateTime());
        CommentEntity newComment = commentRepository.save(commentEntity.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newComment);
    }

    /**
     * Удаление коммента по id
     *
     * @param id
     * @return фраза про удаление коммента
     */
    public ResponseEntity<?> deleteComment(Long id) {
        commentRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Comment with id = %s was deleted.", id));
    }
}


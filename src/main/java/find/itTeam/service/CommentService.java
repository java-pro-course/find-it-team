package find.itTeam.service;

import find.itTeam.dto.CreateComment;
import find.itTeam.entity.CommentEntity;
import find.itTeam.entity.PostEntity;
import find.itTeam.repository.CommentRepository;
import find.itTeam.repository.PostRepository;
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
    public CommentEntity updateComment(Long id, CreateComment comment) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(id);
        if (!commentEntity.isPresent()) {
            return null;
        }
        if (comment.getText() == null | comment.getDateTime() == null) return null;
        // todo (для учеников) сделать метод в репозитории для обновления
        commentEntity.get().setText(comment.getText());
        commentEntity.get().setDateTime(comment.getDateTime());
        return commentRepository.save(commentEntity.get());
    }

    /**
     * Удаление коммента по id
     *
     * @param id
     * @return фраза про удаление коммента
     */
    public String deleteComment(Long id) {
        commentRepository.deleteById(id);
        return "comment has been deleted...";
    }
}


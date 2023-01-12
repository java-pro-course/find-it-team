package find.itTeam.service;

import find.itTeam.dto.CreateComment;
import find.itTeam.entity.CommentEntity;
import find.itTeam.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentEntity createNewComment(CreateComment comment) {
        CommentEntity newComment = new CommentEntity();
        newComment.setText(comment.getText());
        newComment.setDateTime(comment.getDateTime());
        return commentRepository.save(newComment);
    }

    /**
     * Изменение комментария по id
     *
     * @param id
     * @param comment
     */
    public CommentEntity updateComment(Long id, CommentEntity comment) {
        CommentEntity changeComment = commentRepository.findById(id).get();
        changeComment.setText(comment.getText());
        changeComment.setDateTime(comment.getDateTime());
        return commentRepository.save(changeComment);
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


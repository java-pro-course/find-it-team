package find.itTeam.service;

import find.itTeam.dto.CreatePost;
import find.itTeam.entity.PostEntity;
import find.itTeam.repository.PostRepository;
import find.itTeam.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Data
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * Создание нового поста
     *
     * @param post пост, который хочет создать пользователь
     * @return созданный пост
     */
    public ResponseEntity<?> createNewPost(Long authorId, CreatePost post) {
        if (!userRepository.findById(authorId).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The author does not exist!");
        }
        PostEntity newPost = new PostEntity()
                .setContent(post.getContent())
                .setDateTime(LocalDate.now())
                .setPostStatus("Not edited")
                .setAuthor(userRepository.findById(authorId).get());

        postRepository.save(newPost);

        return ResponseEntity.status(HttpStatus.OK).body(newPost);
    }

    /**
     * Изменение поста
     *
     * @param post Пост, который хочет изменить пользователь
     * @return Изменённый пост
     */
    @Transactional
    public ResponseEntity<?> updatePost(CreatePost post, Long id) {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if (!postEntity.isPresent()) {
            // Делать что-то, пока поста с таким ID не существует
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The post does not exist!");
        }

        if (post.getContent() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Content must not be null!");

        }

        postRepository.updateById(post.getContent(), LocalDate.now(), "Edited", id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Updated post %s", id));
    }

    /**
     * Удаление поста по id
     *
     * @param postId id поста
     */
    public ResponseEntity<?> deletePost(Long postId) {
        if(!postRepository.findById(postId).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The post does not exist");
        }
        postRepository.deleteById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Post was deleted!");
    }

    public ResponseEntity<?> getAllPosts() {
        if (postRepository.findAll().isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No posts yet...");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postRepository.findAll());
    }
}
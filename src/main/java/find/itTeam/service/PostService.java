package find.itTeam.service;

import find.itTeam.dto.CreatePost;
import find.itTeam.entity.PostEntity;
import find.itTeam.repository.PostRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class PostService {
    private final PostRepository postRepository;

    /**
     * Создание нового поста
     *
     * @param post пост, который хочет создать пользователь
     * @return созданный пост
     */
    public ResponseEntity<?> createNewPost(CreatePost post) {
        PostEntity newPost = new PostEntity()
                .setContent(post.getContent())
                .setDateTime(post.getDateTime())
                .setPostStatus(post.getPostStatus());

        postRepository.save(newPost);
        return ResponseEntity.status(HttpStatus.OK).body(newPost);
    }

    /**
     * Изменение поста
     *
     * @param post пост, который хочет изменить пользователь
     * @return изменённый пост
     */
    public ResponseEntity<?> updatePost(CreatePost post, Long id) {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if (!postEntity.isPresent()) {
            // Делать что-то, пока поста с таким ID не существует
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The post is not exist!");
        }

        if (post.getContent().equals("") || post.getDateTime() == null || post.getPostStatus().equals("")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("fail");

        }

        postRepository.updateById(post.getContent(), post.getDateTime(), post.getPostStatus(), id);
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
        postRepository.deleteById(postId);
        return ResponseEntity.status(HttpStatus.OK).body("Post was deleted!");
    }
}



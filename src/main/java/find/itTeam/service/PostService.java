package find.itTeam.service;

import find.itTeam.dto.CreateNewPost;
import find.itTeam.entity.PostEntity;
import find.itTeam.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Создание нового поста
     *
     * @param post - пост, который хочет создать пользователь
     * @return - созданный пост
     */
    public ResponseEntity<?> createNewPost(CreateNewPost post) {
        PostEntity newPost = new PostEntity();

        newPost.setContent(post.getContent());
        newPost.setDateTime(post.getDateTime());
        newPost.setPostStatus(post.getPostStatus());
        postRepository.save(newPost);
        return ResponseEntity.status(HttpStatus.OK).body(newPost);
    }

    /**
     * Изменение поста
     *
     * @param post - пост, который хочет изменить пользователь
     * @return - изменённый пост
     */
    public ResponseEntity<?> updatePost(CreateNewPost post, Long id) {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if (!postEntity.isPresent()) {
            // Делать что-то, пока поста с таким ID не существует
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("😰Сей пост не существует...😰");
        }

        // todo (для учеников) сделать метод в репозитории для обновления поста
        PostEntity updPost = new PostEntity();
        updPost.setContent(post.getContent());
        updPost.setDateTime(post.getDateTime());
        updPost.setPostStatus("Изменён");
        postRepository.save(updPost);
        return ResponseEntity.status(HttpStatus.OK).body(updPost);
    }

    /**
     * Удаление поста по id
     *
     * @param postId - id поста
     */
    public ResponseEntity<?> deletePost(Long postId) {
        postRepository.deleteById(postId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted!");
    }
}


package find.itTeam.service;

import find.itTeam.dto.CreatePost;
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
     * @param post пост, который хочет создать пользователь
     * @return созданный пост
     */
    public ResponseEntity<?> createNewPost(CreatePost post) {
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
     * @param post пост, который хочет изменить пользователь
     * @return изменённый пост
     */
    public ResponseEntity<?> updatePost(CreatePost post, Long id) {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if (!postEntity.isPresent()) {
            // Делать что-то, пока поста с таким ID не существует
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("😰Сей пост не существует...😰");
        } else {
            if (post.getContent().equals("") || post.getDateTime() == null || post.getPostStatus().equals("")) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("fail");

            } else {
                postRepository.updateById(post.getContent(), post.getDateTime(), post.getPostStatus(), id);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(String.format("updated user %s", id));
            }
        }
    }

    /**
     * Удаление поста по id
     *
     * @param postId id поста
     */
    public ResponseEntity<?> deletePost(Long postId) {
        postRepository.deleteById(postId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted!");
    }
}



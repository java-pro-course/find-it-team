package find.itTeam.service;

import find.itTeam.dto.CreateNewPost;
import find.itTeam.entity.PostEntity;
import find.itTeam.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class  PostService {
    private final PostRepository postRepository;


    public PostService (PostRepository postRepository){
        this.postRepository = postRepository;
    }

    /**
     * Создание нового поста
     * @param post - пост, который хочет создать пользователь
     * @return - созданный пост
     */
    public PostEntity createNewPost (CreateNewPost post){
        PostEntity newPost = new PostEntity();

        newPost.setContent(post.getContent());
        newPost.setDateTime(post.getDateTime());
        newPost.setPostStatus("");

        return postRepository.save (newPost);
    }

    /**
     * Изменение поста
     * @param post - пост, который хочет изменить пользователь
     * @return - изменённый пост
     */
    public  PostEntity updatePost(PostEntity post){
        PostEntity updPost = new PostEntity();

        updPost.setContent(post.getContent());
        updPost.setDateTime(post.getDateTime());
        updPost.setPostStatus("Изменён");

        return postRepository.save (updPost);
    }

    /**
     * Удаление поста по id
     * @param postId - id поста
     */
    public String deletePost (Long postId){
        postRepository.deleteById(postId);
    return "deleted!";
    }
}


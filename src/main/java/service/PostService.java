package service;



import dto.DeletePostResponse;
import dto.GetAllPostResponse;
import dto.GetPostResponse;
import dto.SavePostRequest;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public GetAllPostResponse getAllPost() {
        GetAllPostResponse response = new GetAllPostResponse();
        List<Post> allPost = postRepository.findAll();

        if (!allPost.isEmpty()) {
           response.setListPost(allPost);
        }

        return response;
    }

    public GetPostResponse getPostById(Long id) {
        GetPostResponse postDto = new GetPostResponse();
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postDto = GetPostResponse.builder()
                    .id(post.get().getId())
                    .author(post.get().getAuthor())
                    .title(post.get().getTitle())
                    .body(post.get().getBody())
                    .build();
        }
        return postDto;
    }

    public DeletePostResponse deletePostById(Long id){
        Boolean isSuccess;
        try {
            postRepository.deleteById(id);
            isSuccess = Boolean.TRUE;
        } catch (Exception e) {
            throw e;
        }
        return DeletePostResponse.builder()
                .isSuccess(isSuccess).build();
    }

    public GetPostResponse savePost(SavePostRequest request) {
        var save = postRepository.saveAndFlush(Post.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .author(request.getAuthor()).build());
        return GetPostResponse.builder()
                .id(save.getId())
                .author(save.getAuthor())
                .title(save.getTitle())
                .body(save.getBody()).build();
    }
}

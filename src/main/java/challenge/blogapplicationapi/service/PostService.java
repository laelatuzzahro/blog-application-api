package challenge.blogapplicationapi.service;




import challenge.blogapplicationapi.dto.*;
import challenge.blogapplicationapi.model.Post;
import challenge.blogapplicationapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public GetPostResponse getPostById(GetPostRequest req) {
        GetPostResponse postDto = new GetPostResponse();
        Optional<Post> post = postRepository.findById(req.getId());
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

    public DeletePostResponse deletePostById(DeletePostRequest req){
        Boolean isSuccess;
        try {
            postRepository.deleteById(req.getId());
            isSuccess = Boolean.TRUE;
        } catch (Exception e) {
            throw e;
        }
        return DeletePostResponse.builder()
                .isSuccess(isSuccess).build();
    }

    public SavePostResponse savePost(SavePostRequest request) {
        var save = postRepository.saveAndFlush(Post.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .author(request.getAuthor()).build());
        return SavePostResponse.builder()
                .id(save.getId())
                .author(save.getAuthor())
                .title(save.getTitle())
                .body(save.getBody()).build();
    }

    public UpdatePostResponse updatePost(UpdatePostRequest request) {
        Optional<Post> post = postRepository.findById(request.getId());
        Post updatePost = new Post();

        if (post.isPresent()) {
            if (Objects.nonNull(request.getAuthor()) && !"".equalsIgnoreCase(request.getAuthor())) {
                post.get().setAuthor(request.getAuthor());
            }

            if (Objects.nonNull(request.getTitle()) && !"".equalsIgnoreCase(request.getTitle())) {
                post.get().setTitle(request.getTitle());
            }

            if (Objects.nonNull(request.getBody()) && !"".equalsIgnoreCase(request.getBody())) {
                post.get().setBody(request.getBody());
            }
            updatePost = postRepository.saveAndFlush(post.get());
        }

        return UpdatePostResponse.builder()
                .id(updatePost.getId())
                .author(updatePost.getAuthor())
                .body(updatePost.getBody())
                .title(updatePost.getTitle()).build();
    }

    public GetAllPostResponse sortByRequest(SortPostRequest request) {
        List<Post> sortPost = new ArrayList<>();

        if (Boolean.TRUE.equals(request.getSortByAuthor())) {
            sortPost = postRepository.findAll(Sort.by(Sort.Direction.ASC,"author"));
        }

        if (Boolean.TRUE.equals(request.getSortByTitle())) {
            sortPost = postRepository.findAll(Sort.by(Sort.Direction.ASC,"title"));
        }

        return GetAllPostResponse.builder()
                .listPost(sortPost).build();
    }
}

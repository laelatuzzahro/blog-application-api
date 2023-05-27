package challenge.blogapplicationapi.controller;

import challenge.blogapplicationapi.dto.*;
import challenge.blogapplicationapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/getallpost")
    public GetAllPostResponse getAllPost() {
        return postService.getAllPost();
    }

    @PostMapping("/getpostbyid")
    public GetPostResponse getPostById(@RequestBody GetPostRequest request) {
        return postService.getPostById(request);
    }

    @PostMapping("/savepost")
    public SavePostResponse savePost(@RequestBody SavePostRequest request) {
        return postService.savePost(request);
    }

    @PostMapping("/deletebyid")
    public DeletePostResponse deletePostById(@RequestBody DeletePostRequest request) {
        return postService.deletePostById(request);
    }

    @PostMapping("/updatepost")
    public UpdatePostResponse updatePost(@RequestBody UpdatePostRequest request) {
        return postService.updatePost(request);
    }

    @PostMapping("/sortpost")
    public GetAllPostResponse sortPostByRequest(@RequestBody SortPostRequest request) {
        return postService.sortByRequest(request);
    }


}

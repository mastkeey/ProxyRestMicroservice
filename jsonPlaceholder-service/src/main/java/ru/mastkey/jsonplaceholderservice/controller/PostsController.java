package ru.mastkey.jsonplaceholderservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mastkey.jsonplaceholderservice.dto.posts.CommentResponse;
import ru.mastkey.jsonplaceholderservice.dto.posts.PostsRequest;
import ru.mastkey.jsonplaceholderservice.dto.posts.PostsResponse;
import ru.mastkey.jsonplaceholderservice.service.PostsService;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postService;

    public PostsController(PostsService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<List<PostsResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }



    @GetMapping("/{id}")
    public ResponseEntity<PostsResponse>  getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }


    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponse>>  getPostCommentsByPostId(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostCommentsByPostId(id));
    }



    @PostMapping("")
    public ResponseEntity<?> addNewPost(@Valid @RequestBody PostsRequest request) {
        return ResponseEntity.ok(postService.addNewPost(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostsResponse> updatePostById(@PathVariable Long id,
                                                        @Valid @RequestBody PostsRequest request) {
        return ResponseEntity.ok(postService.updatePostById(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostsResponse> updatePostFieldsById(@PathVariable Long id,
                                                              @RequestBody PostsRequest postRequest) {
        return ResponseEntity.ok(postService.updatePostFieldsById(id, postRequest));
    }
}

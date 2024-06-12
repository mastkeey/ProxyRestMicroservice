package ru.mastkey.jsonplaceholderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.mastkey.jsonplaceholderservice.client.PostsClient;
import ru.mastkey.jsonplaceholderservice.dto.posts.CommentResponse;
import ru.mastkey.jsonplaceholderservice.dto.posts.PostsRequest;
import ru.mastkey.jsonplaceholderservice.dto.posts.PostsResponse;

import java.util.List;

@Service
@CacheConfig(cacheNames = "PostsCache")
public class PostsService {

    private final PostsClient postsClient;

    public PostsService(PostsClient postsClient) {
        this.postsClient = postsClient;
    }

    public List<PostsResponse> getAllPosts() {
        List<PostsResponse> posts = postsClient.getAllPosts();
        return posts;
    }

    @Cacheable
    public PostsResponse getPostById(Long id) {
        PostsResponse post = postsClient.getPostById(id);
        return post;
    }

    public List<CommentResponse> getPostCommentsByPostId(Long id) {
        List<CommentResponse> comments = postsClient.getPostCommentsByPostId(id);
        return comments;
    }

    public PostsResponse addNewPost(PostsRequest postRequest) {
        PostsResponse post = postsClient.addNewPost(postRequest);
        return post;
    }

    @CacheEvict
    public void deletePostById(Long id) {
        postsClient.deletePostById(id);
    }

    @CachePut(cacheNames = {"PostsCache"}, key = "#id")
    public PostsResponse updatePostById(Long id, PostsRequest postRequest) {
        PostsResponse post = postsClient.updatePostById(id, postRequest);
        return post;
    }
    @CachePut(cacheNames = {"PostsCache"}, key = "#id")
    public PostsResponse updatePostFieldsById(Long id, PostsRequest postRequest) {
        PostsResponse post = postsClient.updatePostFieldsById(id, postRequest);
        return post;
    }
}

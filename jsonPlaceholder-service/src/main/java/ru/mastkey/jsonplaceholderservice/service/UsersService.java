package ru.mastkey.jsonplaceholderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.mastkey.jsonplaceholderservice.client.UsersClient;
import ru.mastkey.jsonplaceholderservice.dto.albums.AlbumsResponse;
import ru.mastkey.jsonplaceholderservice.dto.posts.PostsResponse;
import ru.mastkey.jsonplaceholderservice.dto.users.ToDosResponse;
import ru.mastkey.jsonplaceholderservice.dto.users.UsersRequest;
import ru.mastkey.jsonplaceholderservice.dto.users.UsersResponse;

import java.util.List;

@Service
@CacheConfig(cacheNames = "UsersCache")
public class UsersService {
    private final UsersClient usersClient;

    public UsersService(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    public List<UsersResponse> getAllUsers() {
        List<UsersResponse> users = usersClient.getAllUsers();
        return users;
    }

    @Cacheable
    public UsersResponse getUserById(Long id) {
        UsersResponse user = usersClient.getUserById(id);
        return user;
    }

    public List<PostsResponse> getUserPosts(Long id) {
        List<PostsResponse> posts = usersClient.getUserPosts(id);
        return posts;
    }

    public List<ToDosResponse> getUserToDos(Long id) {
        List<ToDosResponse> todos = usersClient.getUserToDos(id);
        return todos;
    }

    public List<AlbumsResponse> getUserAlbums(Long id) {
        List<AlbumsResponse> albums = usersClient.getUserAlbums(id);
        return albums;
    }

    @CacheEvict
    public void deleteUserById(Long id) {
        usersClient.deleteUserById(id);
    }

    @CachePut
    public UsersResponse updateUserFieldsById(Long id, UsersRequest user) {
        UsersResponse userResponse = usersClient.updateUserFieldsById(id, user);
        return userResponse;
    }

    @CachePut
    public UsersResponse updateUserById(Long id, UsersRequest user) {
        UsersResponse userResponse = usersClient.updateUserById(id, user);
        return userResponse;
    }

    public UsersResponse addNewUser(UsersRequest usersRequest) {
        UsersResponse userResponse = usersClient.addNewUser(usersRequest);
        return userResponse;
    }
}

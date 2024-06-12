package ru.mastkey.jsonplaceholderservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mastkey.jsonplaceholderservice.dto.albums.AlbumsResponse;
import ru.mastkey.jsonplaceholderservice.dto.posts.PostsResponse;
import ru.mastkey.jsonplaceholderservice.dto.users.ToDosResponse;
import ru.mastkey.jsonplaceholderservice.dto.users.UsersRequest;
import ru.mastkey.jsonplaceholderservice.dto.users.UsersResponse;
import ru.mastkey.jsonplaceholderservice.service.UsersService;

import java.util.List;


@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("")
    public ResponseEntity<List<UsersResponse>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse>  getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostsResponse>>  getUserPosts(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserPosts(id));
    }

    @GetMapping("/{id}/todos")
    public ResponseEntity<List<ToDosResponse>>  getUserTodos(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserToDos(id));
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<List<AlbumsResponse>>  getUserAlbums(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserAlbums(id));
    }

    @PostMapping("")
    public ResponseEntity<UsersResponse> addNewUser(@Valid @RequestBody UsersRequest usersRequest) {
        return ResponseEntity.ok(usersService.addNewUser(usersRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        usersService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> updateUserById(@PathVariable Long id,
                                                        @Valid @RequestBody UsersRequest user) {
        return ResponseEntity.ok(usersService.updateUserById(id, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsersResponse> updateUserFieldsById(@PathVariable Long id, @RequestBody UsersRequest user) {
        return ResponseEntity.ok(usersService.updateUserFieldsById(id, user));
    }
}

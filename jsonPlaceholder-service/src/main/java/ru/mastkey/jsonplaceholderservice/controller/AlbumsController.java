package ru.mastkey.jsonplaceholderservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mastkey.jsonplaceholderservice.dto.albums.AlbumsRequest;
import ru.mastkey.jsonplaceholderservice.dto.albums.AlbumsResponse;
import ru.mastkey.jsonplaceholderservice.dto.albums.PhotosResponse;
import ru.mastkey.jsonplaceholderservice.service.AlbumsService;


import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumsService albumsService;

    public AlbumsController(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }


    @GetMapping("")
    public ResponseEntity<List<AlbumsResponse>> getAllAlbums() {
        return ResponseEntity.ok(albumsService.getAllAlbums());
    }



    @GetMapping("/{id}")
    public ResponseEntity<AlbumsResponse> getAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumsService.getAlbumById(id));
    }



    @GetMapping("/{id}/photos")
    public ResponseEntity<List<PhotosResponse>> getPhotosByAlbumId(@PathVariable  Long id) {
        return ResponseEntity.ok(albumsService.getPhotosByAlbumId(id));
    }



    @PostMapping("")
    public ResponseEntity<AlbumsResponse> createAlbum(@Valid @RequestBody AlbumsRequest albumsRequest) {
        return ResponseEntity.ok(albumsService.createAlbum(albumsRequest));
    }



    @PutMapping("/{id}")
    public ResponseEntity<AlbumsResponse> updateAlbum(@PathVariable Long id,
                                                      @Valid @RequestBody AlbumsRequest albumsRequest) {
        return ResponseEntity.ok(albumsService.updateAlbum(id, albumsRequest));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<AlbumsResponse> patchAlbumById(@PathVariable  Long id,
                                                         @RequestBody AlbumsRequest albumsRequest) {
        return ResponseEntity.ok(albumsService.patchAlbumById(id, albumsRequest));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbumById(@PathVariable  Long id) {
        albumsService.deleteAlbumById(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}

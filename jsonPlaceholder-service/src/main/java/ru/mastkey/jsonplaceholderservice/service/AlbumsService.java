package ru.mastkey.jsonplaceholderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.mastkey.jsonplaceholderservice.client.AlbumsClient;
import ru.mastkey.jsonplaceholderservice.dto.albums.AlbumsRequest;
import ru.mastkey.jsonplaceholderservice.dto.albums.AlbumsResponse;
import ru.mastkey.jsonplaceholderservice.dto.albums.PhotosResponse;

import java.util.List;

@Service
@CacheConfig(cacheNames = "AlbumsCache")
public class AlbumsService {

    private final AlbumsClient albumsClient;

    public AlbumsService(AlbumsClient albumsClient) {
        this.albumsClient = albumsClient;
    }

    public List<AlbumsResponse> getAllAlbums() {
        List<AlbumsResponse> albums = albumsClient.getAllAlbums();
        return albums;
    }

    @Cacheable
    public AlbumsResponse getAlbumById(Long id) {
        AlbumsResponse album = albumsClient.getAlbumById(id);
        return album;
    }

    public List<PhotosResponse> getPhotosByAlbumId(Long id) {
        List<PhotosResponse> photos = albumsClient.getPhotosByAlbumId(id);
        return photos;
    }

    @CacheEvict
    public void deleteAlbumById(Long id) {
        albumsClient.deleteAlbumById(id);

    }

    public AlbumsResponse createAlbum(AlbumsRequest albumsRequest) {
        AlbumsResponse albumsResponse = albumsClient.createAlbum(albumsRequest);
        return albumsResponse;
    }

    @CachePut
    public AlbumsResponse updateAlbum(Long id, AlbumsRequest albumsRequest) {
        AlbumsResponse albumsResponse = albumsClient.updateAlbum(id, albumsRequest);
        return albumsResponse;
    }

    @CachePut
    public AlbumsResponse patchAlbumById(Long id, AlbumsRequest albumsRequest) {
        AlbumsResponse albumsResponse = albumsClient.patchAlbumById(id, albumsRequest);
        return albumsResponse;
    }
}

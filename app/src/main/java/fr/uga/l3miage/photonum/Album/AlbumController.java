package fr.uga.l3miage.photonum.Album;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import fr.uga.l3miage.photonum.Page.PageDTO;
import fr.uga.l3miage.photonum.Page.PageMapper;
import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.service.AlbumService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;

import java.util.Collection;
import java.util.Set;
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;
    private final PageMapper pageMapper;

    @Autowired
    public AlbumController(AlbumService albumService, AlbumMapper albumMapper, PageMapper pageMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
        this.pageMapper = pageMapper;
    }

    /*@GetMapping("/Album")
    public Collection<AlbumDTO> albums(@RequestParam(value = "t", required = false) String titre) {
        if (Strings.isBlank(titre)) {
            return albumMapper.entityToDTO(albumService.list());
        }
        return albumMapper.entityToDTO(albumService.findByTitle(titre));
    }*/

    @GetMapping("/Album/{id}")
    public AlbumDTO album(@PathVariable("id") @NotNull Long id) {
        try {
            return albumMapper.entityToDTO(albumService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping("/Album/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumDTO newAlbum(@RequestBody AlbumDTO album) {
        try {
            final var entity = albumService.save(albumMapper.dtoToEntity(album));
            return albumMapper.entityToDTO(entity);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/Album/{id}")
    public AlbumDTO updateAlbum(@PathVariable("id") @NotNull Long id,
                                @RequestBody @Valid AlbumDTO album) {
        try {
            if (album.id().equals(id)) {
                Album albumEntity = albumMapper.dtoToEntity(album);
                var updated = albumService.update(albumEntity);
                return albumMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Album/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable("id") @NotNull Long id) {
        try {
            AlbumDTO albumDTO = this.album(id);
            albumService.delete(albumMapper.dtoToEntity(albumDTO));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Album/{id}/Page")
    public Collection<PageDTO> pages(@PathVariable("id") @NotNull Long albumId) {
        try {
            var album = albumService.get(albumId);
            Set<Page> pages = album.getPagesAlbum();
            return pageMapper.entityToDTO(pages);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }

    }

}
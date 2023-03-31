package fr.uga.l3miage.photonum.Page;

import fr.uga.l3miage.photonum.service.PageService;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class PageController {

    private final PageService pageService;
    private final PageMapper pageMapper;

    @Autowired
    public PageController(PageService pageService, PageMapper pageMapper){
        this.pageService = pageService;
        this.pageMapper = pageMapper;
    }


    @GetMapping("/Page/{id}")
    public PageDTO page(@PathVariable("id") @NotNull Long id) {
        try {
            return pageMapper.entityToDTO(pageService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Image")
    public Collection<PageDTO> pages() {
        return pageMapper.entityToDTO(pageService.list());

    }

    @PostMapping("/Album/{id}/Page")
    @ResponseStatus(HttpStatus.CREATED)
    public PageDTO newPageAlbum(@PathVariable("id") @NotNull Long clientId,
            @RequestBody @Valid PageDTO page) {
        try {
            final var entity = PageService.save(clientId, PageMapper.dtoToEntity(page));
            return pageMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PostMapping("/Calendrier/{id}/Page")
    @ResponseStatus(HttpStatus.CREATED)
    public PageDTO newPageCalendrier(@PathVariable("id") @NotNull Long clientId,
            @RequestBody @Valid PageDTO page) {
        try {
            Page entity = PageService.save(clientId, PageMapper.dtoToEntity(page));
            return pageMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/Page/{id}")
    public PageDTO updatePage(@PathVariable("id") @NotNull Long id,
            @RequestBody @Valid PageDTO page) {
        try {
            if (page.id().equals(id)) {
                Page pageEntity = PageMapper.dtoToEntity(page);
                var updated = pageService.update(pageEntity);
                return pageMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Page/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePage(@PathVariable("id") @NotNull Long id) throws Exception {
        try {
            PageDTO pageDTO = this.page(id);
            pageService.delete(PageMapper.dtoToEntity(pageDTO));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }


    
}

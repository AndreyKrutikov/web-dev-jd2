package by.krutikov.controller;

import by.krutikov.controller.requests.MediaCreateRequest;
import by.krutikov.controller.requests.MediaFindOffsetLimitRequest;
import by.krutikov.controller.requests.MediaUpdateRequest;
import by.krutikov.entity.Media;
import by.krutikov.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media")
public class MediaController {
    static Logger log = Logger.getLogger(MediaController.class);

    private final MediaService mediaService;

    @GetMapping
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllMedia() {
        return new ResponseEntity<>(Collections.singletonMap("all media: ", mediaService.findAll()), HttpStatus.OK);
        // return Collections.singletonMap("result", userService.findAll()); Another way to transfer result to JSON out
    }


    @GetMapping("/find")
    public ResponseEntity<Object> findAllOffsetLimit(@ModelAttribute MediaFindOffsetLimitRequest findLimitOffsetRequest) {
        int verifiedOffset = Integer.parseInt(findLimitOffsetRequest.getOffset());
        int verifiedLimit = Integer.parseInt(findLimitOffsetRequest.getLimit());

        List<Media> media = mediaService.findAll(verifiedOffset, verifiedLimit);
        Map<String, List<Media>> model = Collections.singletonMap("result", media);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findMediaById(@PathVariable String id) {
        long idVerified = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("result", mediaService.findById(idVerified)), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteMediaById(@PathVariable String id) {
        long idVerified = Long.parseLong(id);

        mediaService.delete(idVerified);

        return new ResponseEntity<>(Collections.singletonMap("result", mediaService.findAll()), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> createMedia(@RequestBody MediaCreateRequest createRequest) {
        Media media = new Media();
        media.setPhotoUrl(createRequest.getPhotoUrl());
        media.setDemoUrl(createRequest.getDemoUrl());

        mediaService.create(media);

        List<Media> allMedia = mediaService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("media ws created: ", media.getId());
        model.put("all media:", allMedia);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateMedia(@RequestBody MediaUpdateRequest updateRequest,
                                              @PathVariable String id) {
        long idVerified = Long.parseLong(id);

        Media media = mediaService.findById(idVerified);
        media.setPhotoUrl(updateRequest.getPhotoUrl());
        media.setDemoUrl(updateRequest.getDemoUrl());

        mediaService.update(media);

        List<Media> allMedia = mediaService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("media ws updated: ", media.getId());
        model.put("all media:", allMedia);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}

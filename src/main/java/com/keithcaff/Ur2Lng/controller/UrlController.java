package com.keithcaff.Ur2Lng.controller;

import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RequestMapping("/ur2lng")
@RestController
public class UrlController {
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestBody UrlDto urlDto) {
        //TODO: validate dto
        String hashedUrl = urlService.shortenUrl(urlDto);
        return new ResponseEntity(hashedUrl, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUrlAndRedirect(@PathVariable("id") String id) {
        Optional<Url> url = urlService.getUrl(id);
        if (!url.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.get().getLongUrl()))
                .build();
    }
}
package com.keithcaff.Ur2Lng.controller;

import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.exceptions.UrlNotFoundException;
import com.keithcaff.Ur2Lng.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RequestMapping("/ur2lng")
@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<Void> shortenUrl(@RequestBody @Valid UrlDto urlDto) {
        String encodeUrlId = urlService.shortenUrl(urlDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(encodeUrlId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getUrlAndRedirect(@PathVariable("id") String id) throws UrlNotFoundException {
        Url url = urlService.getUrl(id);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(url.getLongUrl()))
                .build();
    }
}
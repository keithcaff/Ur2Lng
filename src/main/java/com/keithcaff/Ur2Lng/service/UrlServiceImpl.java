package com.keithcaff.Ur2Lng.service;

import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.exceptions.UrlNotFoundException;
import com.keithcaff.Ur2Lng.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UrlKeyGenerator urlKeyGenerator;

    @Override
    public Url getUrl(String encodedId) {
        long urlId = urlKeyGenerator.decode(encodedId);
        Optional<Url> persistedUrl = urlRepository.findById(urlId);
        return persistedUrl.orElseThrow(() ->
         new UrlNotFoundException(String.format("Url with encodedId %s not found", encodedId)));
    }

    @Override
    public String shortenUrl(UrlDto dto) {
        Url persistedUrl;
        Optional<Url> existingUrl = urlRepository.findByLongUrl(dto.getLongUrl());
        persistedUrl = existingUrl.orElseGet(() -> urlRepository.save(new Url(dto.getLongUrl())));
        return urlKeyGenerator.encode(persistedUrl);
    }
}

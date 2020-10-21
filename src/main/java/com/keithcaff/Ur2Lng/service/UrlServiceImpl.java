package com.keithcaff.Ur2Lng.service;

import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    private UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Optional<Url> getUrl(String hashedId) {
        //TODO: get url id from hashedId and lookup longUrl
        return urlRepository.findById(1L);
    }

    @Override
    public String shortenUrl(UrlDto dto) {
        Url url = urlRepository.save(new Url(dto.getLongUrl()));
        //TODO: hash url.id and return hashed id
        return "";
    }
}

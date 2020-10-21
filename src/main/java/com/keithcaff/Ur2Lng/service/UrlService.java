package com.keithcaff.Ur2Lng.service;

import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;

import java.util.Optional;

public interface UrlService {

    Optional<Url> getUrl(String hashedId);
    String shortenUrl(UrlDto url);
}
package com.keithcaff.Ur2Lng.service;

import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;

public interface UrlService {

    Url getUrl(String hashedId);
    String shortenUrl(UrlDto url);
}